package com.example.kira.parcialagenda.Clases;

public class Actividad {
    private String titulo;
    private String contenido;

    public Actividad() {
    }

    public Actividad(String titulo, String contenido) {
        this.titulo = titulo;
        this.contenido = contenido;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
}
