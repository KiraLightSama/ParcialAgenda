package com.example.kira.parcialagenda.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kira.parcialagenda.Clases.Actividad;
import com.example.kira.parcialagenda.R;

import java.util.ArrayList;
import java.util.List;

public class ListaActividadesAdapter extends RecyclerView.Adapter<HolderActividad> {

    private List<Actividad> listaActividad = new ArrayList<>();
    private List<String> keys = new ArrayList<>();
    private Context c;

    public ListaActividadesAdapter(Context c) {
        this.c = c;
    }

    public void addComentario(Actividad a, String key){
        listaActividad.add(a);
        keys.add(key);
        notifyItemInserted(listaActividad.size());
        notifyItemInserted(keys.size());
    }

    @Override
    public HolderActividad onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.cv_item_agenda, parent, false);
        return new HolderActividad(v);
    }

    @Override
    public void onBindViewHolder(final HolderActividad holder, int position) {

        holder.getTvTitulo().setText(listaActividad.get(position).getTitulo());
        holder.getTvContenido().setText(listaActividad.get(position).getContenido());

        holder.setOnclickListener(keys.get(position), c);
    }

    @Override
    public int getItemCount() {
        return listaActividad.size();
    }
}
