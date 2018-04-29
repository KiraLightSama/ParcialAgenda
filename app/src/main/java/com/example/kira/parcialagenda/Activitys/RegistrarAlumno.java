package com.example.kira.parcialagenda.Activitys;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kira.parcialagenda.Agenda;
import com.example.kira.parcialagenda.Clases.Alumno;
import com.example.kira.parcialagenda.Clases.Tutor;
import com.example.kira.parcialagenda.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class RegistrarAlumno extends AppCompatActivity {

    private TextInputLayout tilNombreAlumno, tilNombrePadre, tilTelefono, tilCorreo;
    private EditText etNombreAlumno, etNombrePadre, etTelefono, etCorreo;
    private Button btnAceptar, btnCancelar;

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_alumno);

        tilNombreAlumno = findViewById(R.id.til_nombre_alumno);
        tilNombrePadre = findViewById(R.id.til_nombre_padre);
        tilTelefono = findViewById(R.id.til_telefono_padre);
        tilCorreo = findViewById(R.id.til_correo_padre);

        etNombreAlumno = findViewById(R.id.campo_nombre_alumno);
        etNombrePadre = findViewById(R.id.campo_nombre_padre);
        etTelefono = findViewById(R.id.campo_telefono_padre);
        etCorreo = findViewById(R.id.campo_correo_padre);

        // Referencias Firebase
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        btnAceptar = findViewById(R.id.boton_aceptar_al);
        btnCancelar = findViewById(R.id.boton_cancelar_al);

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarDatos();
                final String nombreAlumno = etNombreAlumno.getText().toString();
                final String nombrePadre = etNombrePadre.getText().toString();
                final String telefono = etTelefono.getText().toString();
                final String correo = etCorreo.getText().toString();

                mAuth.createUserWithEmailAndPassword(correo, telefono).addOnCompleteListener(RegistrarAlumno.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Alumno hijo = new Alumno();
                            hijo.setNombre_completo(nombreAlumno);

                            Tutor padre = new Tutor();
                            padre.setNombre_completo(nombrePadre);
                            padre.setCorreo(correo);
                            padre.setTelefono(telefono);
                            padre.setHijo(hijo);

                            currentUser = mAuth.getCurrentUser();
                            DatabaseReference databaseReference = database.getReference("Profesores/" + Agenda.UID + "/Curso/Padres/" + currentUser.getUid());
                            databaseReference.setValue(padre);

                            startActivity(new Intent(RegistrarAlumno.this, ProcesoMaestro.class));
                            finish();
                        }
                    }
                });
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrarAlumno.this, ProcesoMaestro.class));
                finish();
            }
        });

        etNombreAlumno.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tilNombreAlumno.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etNombrePadre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tilNombrePadre.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etCorreo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                esCorreoValido(String.valueOf(s));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    private boolean esNombreAlumnoValido(String nombre) {
        Pattern patron = Pattern.compile("^[a-zA-Z ]+$");
        if (!patron.matcher(nombre).matches() || nombre.length() > 30) {
            tilNombreAlumno.setError("Nombre inválido");
            return false;
        } else {
            tilNombreAlumno.setError(null);
        }

        return true;
    }

    private boolean esNombrePadreValido(String nombre) {
        Pattern patron = Pattern.compile("^[a-zA-Z ]+$");
        if (!patron.matcher(nombre).matches() || nombre.length() > 30) {
            tilNombrePadre.setError("Nombre inválido");
            return false;
        } else {
            tilNombrePadre.setError(null);
        }

        return true;
    }

    private boolean esTelefonoValido(String telefono) {
        if (!Patterns.PHONE.matcher(telefono).matches()) {
            tilTelefono.setError("Teléfono inválido");
            return false;
        } else {
            tilTelefono.setError(null);
        }

        return true;
    }

    private boolean esCorreoValido(String correo) {
        if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
            tilCorreo.setError("Correo electrónico inválido");
            return false;
        } else {
            tilCorreo.setError(null);
        }

        return true;
    }

    private void validarDatos() {
        String nombreAlumno = tilNombreAlumno.getEditText().getText().toString();
        String nombrePadre = tilNombrePadre.getEditText().getText().toString();
        String telefono = tilTelefono.getEditText().getText().toString();
        String correo = tilCorreo.getEditText().getText().toString();

        boolean a = esNombreAlumnoValido(nombreAlumno);
        boolean b = esTelefonoValido(telefono);
        boolean c = esCorreoValido(correo);
        boolean d = esNombrePadreValido(nombrePadre);
        if (a && b && c && d) {
            // OK, se pasa a la siguiente acción
            Toast.makeText(this, "Se guarda el registro", Toast.LENGTH_LONG).show();
        }

    }
}
