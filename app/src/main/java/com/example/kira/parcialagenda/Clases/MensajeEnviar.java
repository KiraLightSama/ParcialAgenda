package com.example.kira.parcialagenda.Clases;

import java.util.Map;

public class MensajeEnviar extends Mensaje {
    private Map hora;

    public MensajeEnviar() {
    }

    public MensajeEnviar(Map hora) {
        this.hora = hora;
    }

    public MensajeEnviar(String mensaje, String nombre, String type_mensaje, Map hora) {
        super(mensaje, nombre, type_mensaje);
        this.hora = hora;
    }

    public MensajeEnviar(String mensaje, String uriFoto, String nombre, String type_mensaje, Map hora) {
        super(mensaje, uriFoto, nombre, type_mensaje);
        this.hora = hora;
    }

    public Map getHora() {
        return hora;
    }

    public void setHora(Map hora) {
        this.hora = hora;
    }
}
