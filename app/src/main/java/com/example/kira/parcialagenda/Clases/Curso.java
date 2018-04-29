package com.example.kira.parcialagenda.Clases;

public class Curso {
    private String nombre;
    private String colegio;

    public Curso() {
    }

    public Curso(String nombre, String colegio) {
        this.nombre = nombre;
        this.colegio = colegio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getColegio() {
        return colegio;
    }

    public void setColegio(String colegio) {
        this.colegio = colegio;
    }
}
