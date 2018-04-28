package com.example.kira.parcialagenda.Activitys;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.kira.parcialagenda.Clases.Actividad;
import com.example.kira.parcialagenda.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrarActividad extends AppCompatActivity {

    public static String UID;

    private TextInputEditText titulo, contenido;

    private DatabaseReference databaseReference;

    FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_actividad);

        titulo = findViewById(R.id.ar_titulo);
        contenido = findViewById(R.id.ar_contenido);

        Button aceptar = findViewById(R.id.ar_boton_aceptar);
        Button cancelar = findViewById(R.id.ar_boton_cancelar);


        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {
            UID = currentUser.getUid();
            database = FirebaseDatabase.getInstance();
            databaseReference = database.getReference("Profesores/" + UID + "/Curso" + "/Activiades");
        }
        //databaseReference = database.getReference("Profesores/"+ ProcesoMaestro.UID + "/Curso" + "/Activiades");

        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.push().setValue(new Actividad(titulo.getText().toString(), contenido.getText().toString()));
                startActivity(new Intent(RegistrarActividad.this, ProcesoMaestro.class));
                finish();
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrarActividad.this, ProcesoMaestro.class));
                finish();
            }
        });
    }
}
