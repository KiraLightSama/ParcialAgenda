package com.example.kira.parcialagenda.Clases;

public class Tutor {
    private String nombre_completo;
    private String correo;
    private String telefono;
    private Alumno hijo;

    public Tutor() {
    }

    public Tutor(String nombre_completo, String correo, String telefono, Alumno hijo) {
        this.nombre_completo = nombre_completo;
        this.correo = correo;
        this.telefono = telefono;
        this.hijo = hijo;
    }

    public String getNombre_completo() {
        return nombre_completo;
    }

    public void setNombre_completo(String nombre_completo) {
        this.nombre_completo = nombre_completo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Alumno getHijo() {
        return hijo;
    }

    public void setHijo(Alumno hijo) {
        this.hijo = hijo;
    }
}
