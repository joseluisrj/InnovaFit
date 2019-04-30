package com.example.innovafit;

public class Notificacion {

    private Integer idNotificacion;
    private String titulo;
    private String detalle;
    private String fecha;
    private String estado;

    public Notificacion() {
    }

    public Notificacion(Integer idNotificacion, String titulo, String detalle, String fecha, String estado) {
        this.idNotificacion = idNotificacion;
        this.titulo = titulo;
        this.detalle = detalle;
        this.fecha = fecha;
        this.estado = estado;
    }

    public Integer getIdNotificacion() {
        return idNotificacion;
    }

    public void setIdNotificacion(Integer idNotificacion) {
        this.idNotificacion = idNotificacion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
