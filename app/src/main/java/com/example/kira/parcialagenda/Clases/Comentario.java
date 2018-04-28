package com.example.kira.parcialagenda.Clases;

public class Comentario {
    private String mensaje;
    private String nombre;
    private String typeMensaje;

    public Comentario() {
    }

    public Comentario(String mensaje, String nombre, String typeMensaje) {
        this.mensaje = mensaje;
        this.nombre = nombre;
        this.typeMensaje = typeMensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTypeMensaje() {
        return typeMensaje;
    }

    public void setTypeMensaje(String typeMensaje) {
        this.typeMensaje = typeMensaje;
    }
}
