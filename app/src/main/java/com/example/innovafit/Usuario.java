package com.example.innovafit;

public class Usuario {

    private String idUsuario;
    private String clave;
    private String nombres;
    private String apellidos;
    private String imagen;
    private String telefono;
    private String fechaNacimiento;
    private String sexo;
    private int idSede;
    private String estado;

    public Usuario() {
    }

    public Usuario(String idUsuario, String clave, String nombres, String apellidos, String imagen, String telefono, String fechaNacimiento, String sexo, int idSede, String estado) {
        this.idUsuario = idUsuario;
        this.clave = clave;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.imagen = imagen;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
        this.idSede = idSede;
        this.estado = estado;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getIdSede() {
        return idSede;
    }

    public void setIdSede(int idSede) {
        this.idSede = idSede;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
