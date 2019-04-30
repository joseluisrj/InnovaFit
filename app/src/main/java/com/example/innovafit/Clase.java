package com.example.innovafit;

public class Clase {

    private int idClase;
    private String nombre;
    private String entrenador;
    private String fecha;
    private String horaInicio;
    private String horaFin;
    private Integer cantidad;
    private Integer stock;
    private Sede sede;

    public Clase() {
    }

    public Clase(int idClase, String nombre, String entrenador, String fecha, String horaInicio, String horaFin, Integer cantidad, Integer stock, Sede sede) {
        this.idClase = idClase;
        this.nombre = nombre;
        this.entrenador = entrenador;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.cantidad = cantidad;
        this.stock = stock;
        this.sede = sede;
    }

    public int getIdClase() {
        return idClase;
    }

    public void setIdClase(int idClase) {
        this.idClase = idClase;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(String entrenador) {
        this.entrenador = entrenador;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Sede getSede() {
        return sede;
    }

    public void setSede(Sede sede) {
        this.sede = sede;
    }
}