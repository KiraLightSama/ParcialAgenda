package com.example.kira.parcialagenda.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kira.parcialagenda.Clases.ComentarioRecibir;
import com.example.kira.parcialagenda.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListaComentarioAdapter extends RecyclerView.Adapter<HolderComentario> {

    private Context c;
    private List<ComentarioRecibir> comentarioList = new ArrayList<>();

    public ListaComentarioAdapter(Context c) {
        this.c = c;
    }

    public void addMensaje(ComentarioRecibir m){
        comentarioList.add(m);
        notifyItemInserted(comentarioList.size());
    }

    @Override
    public HolderComentario onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.cv_agenda_comentario, parent, false);
        return new HolderComentario(v);
    }

    @Override
    public void onBindViewHolder(HolderComentario holder, int position) {
        holder.getTxtUser().setText(comentarioList.get(position).getNombre());
        holder.getTxtMensaje().setText(comentarioList.get(position).getMensaje());

        Long codigoHora = comentarioList.get(position).getHora();
        Date d = new Date(codigoHora);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");

        holder.getTxtHora().setText(sdf.format(d));

    }

    @Override
    public int getItemCount() {
        return comentarioList.size();
    }
}
