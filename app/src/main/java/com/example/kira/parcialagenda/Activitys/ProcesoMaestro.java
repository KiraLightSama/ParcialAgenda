package com.example.kira.parcialagenda.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.kira.parcialagenda.Agenda;
import com.example.kira.parcialagenda.Chat;
import com.example.kira.parcialagenda.R;
import com.google.firebase.auth.FirebaseAuth;

public class ProcesoMaestro extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    /*public static String UID;
    public static String NOMBRE_USUARIO;

    private ListaActividadesAdapter adapter;
    private RecyclerView rvListaActividad;

    private DatabaseReference databaseReference;

    FirebaseUser currentUser;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proceso_maestro);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FirebaseAuth mAuth = FirebaseAuth.getInstance();

        rvListaActividad = findViewById(R.id.rvListaActividades);

        adapter = new ListaActividadesAdapter(this);
        LinearLayoutManager l = new LinearLayoutManager(this);
        rvListaActividad.setLayoutManager(l);
        rvListaActividad.setAdapter(adapter);
*/
        /*
        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Agregar Actividades Agenda<--------------------------------------------
                startActivity(new Intent(ProcesoMaestro.this, RegistrarActividad.class));
                finish();
            }
        });
*/
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.contenedor, new Agenda()).commit();
/*
        currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {
            UID = currentUser.getUid();
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            databaseReference = database.getReference("Profesores/" + ProcesoMaestro.UID + "/Curso" + "/Activiades");
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

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
*/
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.proceso_maestro, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fragmentManager = getSupportFragmentManager();

        if (id == R.id.lista_alumnos) {
            startActivity(new Intent(ProcesoMaestro.this, RegistrarAlumno.class));
            finish();
        } else if (id == R.id.nav_agenda) {
            fragmentManager.beginTransaction().replace(R.id.contenedor, new Agenda()).commit();
        } else if (id == R.id.nav_chat) {
            fragmentManager.beginTransaction().replace(R.id.contenedor, new Chat()).commit();
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        } else if (id == R.id.nav_logout) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(ProcesoMaestro.this, Login.class));
            finish();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    /*private void setScrollBar (){
        rvListaActividad.scrollToPosition(adapter.getItemCount()-1);
    }*/
}
