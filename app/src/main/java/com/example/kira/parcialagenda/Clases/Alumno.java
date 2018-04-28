package com.example.kira.parcialagenda.Clases;

public class Alumno {
    private String nombre_completo;
    private Tutor padre;

    public Alumno() {
    }

    public Alumno(String nombre_completo) {
        this.nombre_completo = nombre_completo;
    }

    public String getNombre_completo() {
        return nombre_completo;
    }

    public void setNombre_completo(String nombre_completo) {
        this.nombre_completo = nombre_completo;
    }
}
