package com.example.innovafit;

public class Sede {

    private int idSede;
    private String nombre;
    private String direccion;
    private String marcador1;
    private String marcador2;
    private String marcador3;
    private String estado;

    public Sede() {
    }

    public Sede(int idSede, String nombre, String direccion, String marcador1, String marcador2, String marcador3, String estado) {
        this.idSede = idSede;
        this.nombre = nombre;
        this.direccion = direccion;
        this.marcador1 = marcador1;
        this.marcador2 = marcador2;
        this.marcador3 = marcador3;
        this.estado = estado;
    }

    public int getIdSede() {
        return idSede;
    }

    public void setIdSede(int idSede) {
        this.idSede = idSede;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getMarcador1() {
        return marcador1;
    }

    public void setMarcador1(String marcador1) {
        this.marcador1 = marcador1;
    }

    public String getMarcador2() {
        return marcador2;
    }

    public void setMarcador2(String marcador2) {
        this.marcador2 = marcador2;
    }

    public String getMarcador3() {
        return marcador3;
    }

    public void setMarcador3(String marcador3) {
        this.marcador3 = marcador3;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}