package com.example.kira.parcialagenda.Adapters;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kira.parcialagenda.Activitys.ProcesoMaestro;
import com.example.kira.parcialagenda.Clases.Actividad;
import com.example.kira.parcialagenda.Clases.ComentarioEnviar;
import com.example.kira.parcialagenda.Clases.ComentarioRecibir;
import com.example.kira.parcialagenda.Clases.Persona;
import com.example.kira.parcialagenda.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListaActividadesAdapter extends RecyclerView.Adapter<HolderActividad> {

    private List<Actividad> listaActividad = new ArrayList<>();
    private Context c;

    public ListaActividadesAdapter(Context c) {
        this.c = c;
    }

    public void addComentario(Actividad a){
        listaActividad.add(a);
        notifyItemInserted(listaActividad.size());
    }

    @Override
    public HolderActividad onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.cv_item_agenda, parent, false);
        return new HolderActividad(v);
    }

    private static String NOMBRE_USUARIO;

    FirebaseDatabase database;
    private DatabaseReference databaseReference;

    private ListaComentarioAdapter adapter;

    @Override
    public void onBindViewHolder(final HolderActividad holder, int position) {

        holder.getTvTitulo().setText(listaActividad.get(position).getTitulo());
        holder.getTvContenido().setText(listaActividad.get(position).getContenido());

        adapter = new ListaComentarioAdapter(c);
        LinearLayoutManager l = new LinearLayoutManager(c);

        holder.getRvComentarios().setLayoutManager(l);
        holder.getRvComentarios().setAdapter(adapter);


        FirebaseAuth mAuth = FirebaseAuth.getInstance();


        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            database = FirebaseDatabase.getInstance();
            databaseReference = database.getReference("Profesores/" + ProcesoMaestro.UID + "/Curso/Activiades/Comentarios");
        }

        holder.getImSendComentario().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.push().setValue(new ComentarioEnviar(holder.getTvComentario().getText().toString(), NOMBRE_USUARIO, "1", ServerValue.TIMESTAMP));
                holder.getTvComentario().setText("");
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
                holder.getRvComentarios().scrollToPosition(adapter.getItemCount() - 1);
                //setScrollBar();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaActividad.size();
    }
}
