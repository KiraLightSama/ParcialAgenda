package com.example.kira.parcialagenda.Adapters;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.kira.parcialagenda.Activitys.ProcesoMaestro;
import com.example.kira.parcialagenda.Agenda;
import com.example.kira.parcialagenda.Clases.ComentarioEnviar;
import com.example.kira.parcialagenda.Clases.ComentarioRecibir;
import com.example.kira.parcialagenda.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

public class HolderActividad extends RecyclerView.ViewHolder{

    private TextView tvTitulo, tvContenido;
    private EditText etComentario;
    private ImageButton imSendComentario;
    private RecyclerView rvComentarios;

    public HolderActividad(View itemView) {
        super(itemView);
        tvTitulo = itemView.findViewById(R.id.tvTitulo);
        tvContenido = itemView.findViewById(R.id.tvBody);
        rvComentarios = itemView.findViewById(R.id.rvComentarios);
        etComentario = itemView.findViewById(R.id.cvComentarios);
        imSendComentario = itemView.findViewById(R.id.cvEnviarComentairo);
    }

    public TextView getTvTitulo() {
        return tvTitulo;
    }

    public void setTvTitulo(TextView tvTitulo) {
        this.tvTitulo = tvTitulo;
    }

    public TextView getTvContenido() {
        return tvContenido;
    }

    public void setEtContenido(TextView tvContenido) {
        this.tvContenido = tvContenido;
    }

    public EditText getEtComentario() {
        return etComentario;
    }

    public void setEtComentario(EditText etComentario) {
        this.etComentario = etComentario;
    }

    public ImageButton getImSendComentario() {
        return imSendComentario;
    }

    public void setImSendComentario(ImageButton imSendComentario) {
        this.imSendComentario = imSendComentario;
    }

    public RecyclerView getRvComentarios() {
        return rvComentarios;
    }

    public void setRvComentarios(RecyclerView rvComentarios) {
        this.rvComentarios = rvComentarios;
    }
    private DatabaseReference databaseReference;

    private ListaComentarioAdapter adapter;
    private final static String NOMBRE_USUARIO = Agenda.NOMBRE_USUARIO;

    public void setOnclickListener(String keys, Context c){
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        adapter = new ListaComentarioAdapter(c);
        LinearLayoutManager l = new LinearLayoutManager(c);

        getRvComentarios().setLayoutManager(l);
        getRvComentarios().setAdapter(adapter);

        databaseReference = database.getReference("Profesores/" + Agenda.UID + "/Curso/Activiades/"+ keys +"/Comentarios");
        imSendComentario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.push().setValue(new ComentarioEnviar(getEtComentario().getText().toString(), "", "1", ServerValue.TIMESTAMP));
                getEtComentario().setText("");
            }
        });

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ComentarioRecibir recibir = dataSnapshot.getValue(ComentarioRecibir.class);
                adapter.addMensaje(recibir);
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
                getRvComentarios().scrollToPosition(adapter.getItemCount() - 1);
                setScrollBar();
            }
        });
    }
    private void setScrollBar (){
        getRvComentarios().scrollToPosition(adapter.getItemCount()-1);
    }
}
