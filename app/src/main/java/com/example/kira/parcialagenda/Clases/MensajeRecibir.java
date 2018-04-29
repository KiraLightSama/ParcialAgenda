package com.example.kira.parcialagenda.Clases;

public class MensajeRecibir extends  Mensaje{
    private Long hora;

    public MensajeRecibir() {
    }

    public MensajeRecibir(Long hora) {
        this.hora = hora;
    }

    public MensajeRecibir(String mensaje, String uriFoto, String nombre, String fotoPerfil, String type_mensaje, Long hora) {
        super(mensaje, uriFoto, nombre, type_mensaje);
        this.hora = hora;
    }

    public Long getHora() {
        return hora;
    }

    public void setHora(Long hora) {
        this.hora = hora;
    }
}
