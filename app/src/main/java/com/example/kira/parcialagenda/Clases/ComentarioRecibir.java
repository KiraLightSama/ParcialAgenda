package com.example.kira.parcialagenda.Clases;

public class ComentarioRecibir extends Comentario{
    private Long hora;

    public ComentarioRecibir() {
    }

    public ComentarioRecibir(Long hora) {
        this.hora = hora;
    }

    public ComentarioRecibir(String mensaje, String nombre, String typeMensaje, Long hora) {
        super(mensaje, nombre, typeMensaje);
        this.hora = hora;
    }

    public Long getHora() {
        return hora;
    }

    public void setHora(Long hora) {
        this.hora = hora;
    }
}
