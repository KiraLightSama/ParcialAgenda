package com.example.kira.parcialagenda.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.kira.parcialagenda.R;

public class HolderActividad extends RecyclerView.ViewHolder {

    private TextView tvTitulo, tvContenido;
    private EditText tvComentario;
    private ImageButton imSendComentario;
    private RecyclerView rvComentarios;


    public HolderActividad(View itemView) {
        super(itemView);
        tvTitulo = itemView.findViewById(R.id.tvTitulo);
        tvContenido = itemView.findViewById(R.id.tvBody);
        imSendComentario = itemView.findViewById(R.id.cvEnviarComentairo);
        rvComentarios = itemView.findViewById(R.id.rvComentarios);

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

    public void setTvContenido(TextView tvContenido) {
        this.tvContenido = tvContenido;
    }

    public EditText getTvComentario() {
        return tvComentario;
    }

    public void setTvComentario(EditText tvComentario) {
        this.tvComentario = tvComentario;
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
}
