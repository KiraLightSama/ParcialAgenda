package com.example.kira.parcialagenda;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kira.parcialagenda.Activitys.ProcesoMaestro;
import com.example.kira.parcialagenda.Activitys.RegistrarActividad;
import com.example.kira.parcialagenda.Adapters.ListaActividadesAdapter;
import com.example.kira.parcialagenda.Clases.Actividad;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Agenda extends Fragment {

    //este codigo es nuevo

    public static String UID;
    public static String NOMBRE_USUARIO;

    private ListaActividadesAdapter adapter;
    private RecyclerView rvListaActividad;

    private DatabaseReference databaseReference;

    FirebaseUser currentUser;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_agenda, container, false);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        rvListaActividad = view.findViewById(R.id.rvListaActividades);

        adapter = new ListaActividadesAdapter(getActivity());
        LinearLayoutManager l = new LinearLayoutManager(getActivity());
        rvListaActividad.setLayoutManager(l);
        rvListaActividad.setAdapter(adapter);

        currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {
            UID = currentUser.getUid();
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            databaseReference = database.getReference("Profesores/" + Agenda.UID + "/Curso/Activiades");
        }
        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                setScrollBar();
            }
        });
        databaseReference.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Actividad actividad = dataSnapshot.getValue(Actividad.class);
                adapter.addComentario(actividad, dataSnapshot.getKey());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
                    @Override
                    public void onItemRangeInserted(int positionStart, int itemCount) {
                        super.onItemRangeInserted(positionStart, itemCount);
                        setScrollBar();
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        FloatingActionButton fab = view.findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Agregar Actividades Agenda<--------------------------------------------
                startActivity(new Intent(getActivity(), RegistrarActividad.class));
            }
        });

        return view;
    }
    private void setScrollBar (){
        rvListaActividad.scrollToPosition(adapter.getItemCount()-1);
    }
}
