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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kira.parcialagenda.Clases.Curso;
import com.example.kira.parcialagenda.Clases.Persona;
import com.example.kira.parcialagenda.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

import fr.ganfra.materialspinner.MaterialSpinner;

public class Registrar extends AppCompatActivity {

    private TextInputLayout tilNombre;
    private TextInputLayout tilTelefono;
    private TextInputLayout tilCorreo;
    private TextInputLayout tillPassword;
    private TextInputLayout tillVerificar;

    private EditText campoNombre;
    private EditText campoTelefono;
    private EditText campoCorreo;
    private EditText campoPassword;
    private EditText campoVerificar;

    private Button botonAceptar;
    private Button botonCancelar;

    private MaterialSpinner nivel;

    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private FirebaseUser currentUser;

    private String nombreCurso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        // Referencias TILs
        tilNombre = findViewById(R.id.til_nombre);
        tilTelefono = findViewById(R.id.til_telefono);
        tilCorreo = findViewById(R.id.til_correo);
        tillPassword = findViewById(R.id.til_password);
        tillVerificar = findViewById(R.id.til_verificar);

        // Referencia Edits
        campoNombre = findViewById(R.id.campo_nombre);
        campoTelefono = findViewById(R.id.campo_telefono);
        campoCorreo = findViewById(R.id.campo_correo);
        campoPassword = findViewById(R.id.campo_password);
        campoVerificar = findViewById(R.id.campo_verificar);

        // Referencias Firebase
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        // Referencia Botón
        Button botonAceptar = findViewById(R.id.boton_aceptar);
        Button botonCancelar = findViewById(R.id.boton_cancelar);

        nivel = findViewById(R.id.select_nivel);
        String[] Items = {"Primero Basico", "Segundo Basico", "Tercero Basico", "Cuarto Basico", "Quinto Basico"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, Items);
        nivel.setAdapter(adapter);

        nivel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != -1){
                    nombreCurso = nivel.getItemAtPosition(position).toString();
                } else {
                    nivel.setError("Seleccione un nivel");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        botonAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            validarDatos();

            final String nombre = campoNombre.getText().toString();
            final String correo = campoCorreo.getText().toString();
            final String numero = campoTelefono.getText().toString();
            final String contraseña = campoPassword.getText().toString();

            mAuth.createUserWithEmailAndPassword(correo, contraseña).addOnCompleteListener(Registrar.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Persona usuario = new Persona();
                        Toast.makeText(Registrar.this, "Se registro correctamente", Toast.LENGTH_SHORT).show();;
                        usuario.setNombre_completo(nombre);
                        usuario.setCorreo(correo);
                        usuario.setTelefono(numero);

                        currentUser = mAuth.getCurrentUser();
                        DatabaseReference databaseReference = database.getReference("Profesores/" + currentUser.getUid());
                        databaseReference.setValue(usuario);

                        Curso curso = new Curso();
                        curso.setNombre(nombreCurso);

                        databaseReference = database.getReference("Profesores/"+currentUser.getUid()+"/Curso");
                        databaseReference.setValue(curso);

                        finish();
                    } else {
                        Toast.makeText(Registrar.this, "Error al registrarse usuario ya existe", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            }
        });

        botonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Registrar.this, Login.class));
                finish();
            }
        });

        campoNombre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tilNombre.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        campoCorreo.addTextChangedListener(new TextWatcher() {
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

        campoPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                tillPassword.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        campoVerificar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                tillVerificar.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private boolean esNombreValido(String nombre) {
        Pattern patron = Pattern.compile("^[a-zA-Z ]+$");
        if (!patron.matcher(nombre).matches() || nombre.length() > 30) {
            tilNombre.setError("Nombre inválido");
            return false;
        } else {
            tilNombre.setError(null);
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

    public boolean validarContraseña (){
        String contraseña, contraseñaRepetida;
        contraseña = campoPassword.getText().toString();
        contraseñaRepetida = campoVerificar.getText().toString();
        if (contraseña.equals(contraseñaRepetida)) {
            if (contraseña.length()>=6 && contraseña.length()<= 16) {
                tillPassword.setError(null);
                return true;
            } else {
                tillPassword.setError("Contraseña muy corta");
                return false;
            }
        } else {
            tillVerificar.setError("Las contraseñas no coinciden");
            return false;
        }
    }

    private void validarDatos() {
        String nombre = tilNombre.getEditText().getText().toString();
        String telefono = tilTelefono.getEditText().getText().toString();
        String correo = tilCorreo.getEditText().getText().toString();

        boolean a = esNombreValido(nombre);
        boolean b = esTelefonoValido(telefono);
        boolean c = esCorreoValido(correo);
        boolean d = validarContraseña();

        if (a && b && c && d) {
            // OK, se pasa a la siguiente acción
            Toast.makeText(this, "Se guarda el registro", Toast.LENGTH_LONG).show();
        }

    }
}