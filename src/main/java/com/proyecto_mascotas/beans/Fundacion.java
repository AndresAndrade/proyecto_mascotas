package com.proyecto_mascotas.beans;

public class Fundacion {
    private int idFundacion;
    private String nombreFundacion;
    private String telefono;
    private String email;
    private int idCiudad;
    private String ciudadFundacion;
    private String departamentoFundacion;

    /*Para las listas desplegables*/
    public Fundacion(int idFundacion, String nombreFundacion, String telefono, String email, int idCiudad) {
        this.idFundacion = idFundacion;
        this.nombreFundacion = nombreFundacion;
        this.telefono = telefono;
        this.email = email;
        this.idCiudad = idCiudad;
    }

    /*Para insertar un registro en la base de datos*/
    public Fundacion(String nombreFundacion, String telefono, String email, int idCiudad) {
        this.nombreFundacion = nombreFundacion;
        this.telefono = telefono;
        this.email = email;
        this.idCiudad = idCiudad;
    }

    /*Para listar en el HTML*/
    public Fundacion(int idFundacion, String nombreFundacion, String telefono, String email, String ciudadFundacion,
                     String departamentoFundacion) {
        this.idFundacion = idFundacion;
        this.nombreFundacion = nombreFundacion;
        this.telefono = telefono;
        this.email = email;
        this.ciudadFundacion = ciudadFundacion;
        this.departamentoFundacion = departamentoFundacion;
    }

    public Fundacion(int idFundacion, String nombreFundacion) {
        this.idFundacion = idFundacion;
        this.nombreFundacion = nombreFundacion;
    }

    public int getIdFundacion() {
        return idFundacion;
    }

    public void setIdFundacion(int idFundacion) {
        this.idFundacion = idFundacion;
    }

    public String getNombreFundacion() {
        return nombreFundacion;
    }

    public void setNombreFundacion(String nombreFundacion) {
        this.nombreFundacion = nombreFundacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(int idCiudad) {
        this.idCiudad = idCiudad;
    }

    public String getCiudadFundacion() {
        return ciudadFundacion;
    }

    public void setCiudadFundacion(String ciudadFundacion) {
        this.ciudadFundacion = ciudadFundacion;
    }

    public String getDepartamentoFundacion() {
        return departamentoFundacion;
    }

    public void setDepartamentoFundacion(String departamentoFundacion) {
        this.departamentoFundacion = departamentoFundacion;
    }

    @Override
    public String toString() {
        return "Fundacion{" +
                "idFundacion=" + idFundacion +
                ", nombreFundacion='" + nombreFundacion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                ", idCiudad=" + idCiudad +
                ", ciudadFundacion='" + ciudadFundacion + '\'' +
                ", departamentoFundacion='" + departamentoFundacion + '\'' +
                '}';
    }
}
