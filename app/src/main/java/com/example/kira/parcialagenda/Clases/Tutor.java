package com.example.kira.parcialagenda.Clases;

public class Tutor {
    private String nombre_completo;
    private String correo;
    private String password;
    private Alumno hijo;

    public Tutor() {
    }

    public Tutor(String nombre_completo, String correo, String password, Alumno hijo) {
        this.nombre_completo = nombre_completo;
        this.correo = correo;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Alumno getHijo() {
        return hijo;
    }

    public void setHijo(Alumno hijo) {
        this.hijo = hijo;
    }
}
