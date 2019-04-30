package com.example.innovafit;

public class Membresia {

    private Integer idMembresia;
    private String nombre;
    private String fechaInicio;
    private String fechaFin;
    private Integer stock;
    private TipoMembresia tipoMembresia;
    private Usuario usuario;

    public Membresia() {
    }

    public Membresia(Integer idMembresia, String nombre, String fechaInicio, String fechaFin, Integer stock, TipoMembresia tipoMembresia, Usuario usuario) {
        this.idMembresia = idMembresia;
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.stock = stock;
        this.tipoMembresia = tipoMembresia;
        this.usuario = usuario;
    }

    public Integer getIdMembresia() {
        return idMembresia;
    }

    public void setIdMembresia(Integer idMembresia) {
        this.idMembresia = idMembresia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public TipoMembresia getTipoMembresia() {
        return tipoMembresia;
    }

    public void setTipoMembresia(TipoMembresia tipoMembresia) {
        this.tipoMembresia = tipoMembresia;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
