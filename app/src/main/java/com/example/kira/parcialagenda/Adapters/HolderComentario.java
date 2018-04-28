package com.example.kira.parcialagenda.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.kira.parcialagenda.R;

public class HolderComentario extends RecyclerView.ViewHolder {

    private TextView txtUser, txtHora, txtMensaje;

    public HolderComentario(View itemView) {
        super(itemView);
        txtUser = itemView.findViewById(R.id.cvAgendaUser);
        txtHora = itemView.findViewById(R.id.cvAgendaHora);
        txtMensaje = itemView.findViewById(R.id.cvAgendaComentario);
    }

    public TextView getTxtUser() {
        return txtUser;
    }

    public void setTxtUser(TextView txtUser) {
        this.txtUser = txtUser;
    }

    public TextView getTxtHora() {
        return txtHora;
    }

    public void setTxtHora(TextView txtHora) {
        this.txtHora = txtHora;
    }

    public TextView getTxtMensaje() {
        return txtMensaje;
    }

    public void setTxtMensaje(TextView txtMensaje) {
        this.txtMensaje = txtMensaje;
    }
}
