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
import android.widget.TextView;
import android.widget.Toast;

import com.example.kira.parcialagenda.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private Button btnLogin;
    private EditText etUser, etPass;
    private TextView tvRegistrar;
    private TextInputLayout tilUser, tilPass;

    private FirebaseAuth mAuth;
    FirebaseUser currentUser;

    public static  String UID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUser = findViewById(R.id.lgUser);
        etPass = findViewById(R.id.lgPass);
        tvRegistrar = findViewById(R.id.lgRegistrar);

        tilUser = findViewById(R.id.lgTilUser);
        tilPass = findViewById(R.id.lgTilPass);

        btnLogin = findViewById(R.id.lgLogin);

        mAuth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String correo = etUser.getText().toString();
                if (esCorreoValido(correo) && validarContraseña()) {
                    String contraseña = etPass.getText().toString();
                    mAuth.signInWithEmailAndPassword(correo,contraseña).
                            addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        nextActivity();
                                    } else {
                                        Toast.makeText(Login.this, "Error credenciales incorrectas", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                } else {
                    Toast.makeText(Login.this, "Error al rellenar los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Registrar.class));
            }
        });

        etUser.addTextChangedListener(new TextWatcher() {
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

        etPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tilPass.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private boolean esCorreoValido(String correo) {
        if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
            tilUser.setError("Correo electrónico inválido");
            return false;
        } else {
            tilUser.setError(null);
        }

        return true;
    }

    public boolean validarContraseña (){
        String contraseña, contraseñaRepetida;
        contraseña = etPass.getText().toString();
            if (contraseña.length()>=6 && contraseña.length()<= 16) {
                tilPass.setError(null);
                return true;
            } else {
                tilPass.setError("Contraseña muy corta");
                return false;
            }
    }

    @Override
    protected void onResume() {
        super.onResume();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            UID = currentUser.getUid();
            nextActivity();
        }
    }

    private void nextActivity (){
        Toast.makeText(Login.this, "Se logeo correctamente", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(Login.this, ProcesoMaestro.class));
        finish();
    }
}
