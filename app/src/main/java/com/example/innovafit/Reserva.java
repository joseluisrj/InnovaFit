package com.example.innovafit;

public class Reserva {

    private Integer idReserva;
    private Clase clase;
    private Membresia membresia;
    private String estado;

    public Reserva() {
    }

    public Reserva(Integer idReserva, Clase clase, Membresia membresia, String estado) {
        this.idReserva = idReserva;
        this.clase = clase;
        this.membresia = membresia;
        this.estado = estado;
    }

    public Integer getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Integer idReserva) {
        this.idReserva = idReserva;
    }

    public Clase getClase() {
        return clase;
    }

    public void setClase(Clase clase) {
        this.clase = clase;
    }

    public Membresia getMembresia() {
        return membresia;
    }

    public void setMembresia(Membresia membresia) {
        this.membresia = membresia;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
