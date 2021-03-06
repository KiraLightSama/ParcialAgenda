package com.example.kira.parcialagenda.Activitys;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.kira.parcialagenda.Adapters.AdapterMensajes;
import com.example.kira.parcialagenda.Clases.MensajeEnviar;
import com.example.kira.parcialagenda.Clases.MensajeRecibir;
import com.example.kira.parcialagenda.Clases.Persona;
import com.example.kira.parcialagenda.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvMensajes;
    private EditText txtMesaje;
    private FloatingActionButton btnEnviar;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private FirebaseStorage storage;

    private FirebaseAuth mAuth;
    FirebaseUser currentUser;

    private static final int PHOTO_SEND = 1;
    private String fotoPerfilCadena;
    private String NOMBRE_USUARIO;
    public static String UID;

    private AdapterMensajes adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
        txtMesaje = findViewById(R.id.txtMensaje);
        btnEnviar = findViewById(R.id.btnEnviarMensaje);
        ImageButton btnEnviarFoto = findViewById(R.id.btnEnviarFoto);
        rvMensajes = findViewById(R.id.rvMensajes);

        fotoPerfilCadena = "";

        adapter = new AdapterMensajes(this);
        LinearLayoutManager l = new LinearLayoutManager(this);
        rvMensajes.setLayoutManager(l);
        rvMensajes.setAdapter(adapter);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Profesores/" + MainActivity.UID + "/Curso/Chats");
        storage = FirebaseStorage.getInstance();


        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.push().setValue(new MensajeEnviar(txtMesaje.getText().toString(), NOMBRE_USUARIO,fotoPerfilCadena,"1", ServerValue.TIMESTAMP));
                txtMesaje.setText("");
            }
        });

        btnEnviarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.setType("image/jpeg");
                i.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                startActivityForResult(Intent.createChooser(i, "Selecciona una foto"), PHOTO_SEND);
            }
        });

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                MensajeRecibir m = dataSnapshot.getValue(MensajeRecibir.class);
                adapter.addMensaje(m);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                setScrollBar();
            }
        });
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PHOTO_SEND && resultCode == RESULT_OK){
            Uri u = data.getData();
            StorageReference storageReference = storage.getReference("imagenes_chat");
            final StorageReference fotoReferencia = storageReference.child(u.getLastPathSegment());
            fotoReferencia.putFile(u).addOnSuccessListener(this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Uri u = taskSnapshot.getDownloadUrl();
                    MensajeEnviar m = new MensajeEnviar(NOMBRE_USUARIO + " te ha enviado una foto", u.toString(), NOMBRE_USUARIO, "2", ServerValue.TIMESTAMP);
                    databaseReference.push().setValue(m);
                }
            });
        }*/
    }
/*
    @Override
    public void onResume() {
        super.onResume();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            btnEnviar.setEnabled(false);
            UID = mAuth.getUid();
            DatabaseReference databaseReference = database.getReference("Profesores/" + UID);
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Persona usuario = dataSnapshot.getValue(Persona.class);
                    NOMBRE_USUARIO = usuario.getNombre_completo();
                    btnEnviar.setEnabled(true);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        } else {
            startActivity(new Intent(MainActivity.this, Login.class));
        }
    }
    private void setScrollBar (){
        rvMensajes.scrollToPosition(adapter.getItemCount()-1);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(MainActivity.this, ProcesoMaestro.class));
        finish();
    }*/
}
