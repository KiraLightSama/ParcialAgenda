package com.example.kira.parcialagenda.Clases;

import java.util.Map;

public class ComentarioEnviar extends Comentario{
    private Map hora;

    public ComentarioEnviar() {
    }

    public ComentarioEnviar(Map hora) {
        this.hora = hora;
    }

    public ComentarioEnviar(String mensaje, String nombre, String typeMensaje, Map hora) {
        super(mensaje, nombre, typeMensaje);
        this.hora = hora;
    }

    public Map getHora() {
        return hora;
    }

    public void setHora(Map hora) {
        this.hora = hora;
    }
}
