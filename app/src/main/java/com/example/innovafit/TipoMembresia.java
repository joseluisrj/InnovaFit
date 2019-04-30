package com.example.innovafit;

import java.math.BigDecimal;

public class TipoMembresia {

    private Integer idTipo;
    private String nombre;
    private BigDecimal costo;
    private Integer cantidad;
    private String estado;

    public TipoMembresia() {
    }

    public TipoMembresia(Integer idTipo, String nombre, BigDecimal costo, Integer cantidad, String estado) {
        this.idTipo = idTipo;
        this.nombre = nombre;
        this.costo = costo;
        this.cantidad = cantidad;
        this.estado = estado;
    }

    public Integer getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(Integer idTipo) {
        this.idTipo = idTipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
