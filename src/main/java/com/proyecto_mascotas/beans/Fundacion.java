package com.proyecto_mascotas.beans;

public class Fundacion {
    private int idFundacion;
    private String nombreFundacion;
    private String telefono;
    private String email;
    private int idCiudad;

    public Fundacion(int idFundacion, String nombreFundacion, String telefono, String email, int idCiudad) {
        this.idFundacion = idFundacion;
        this.nombreFundacion = nombreFundacion;
        this.telefono = telefono;
        this.email = email;
        this.idCiudad = idCiudad;
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

    public int getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(int idCiudad) {
        this.idCiudad = idCiudad;
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

    @Override
    public String toString() {
        return "Fundacion{" +
                "idFundacion=" + idFundacion +
                ", nombreFundacion='" + nombreFundacion + '\'' +
                ", ubicacion=" + idCiudad +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
