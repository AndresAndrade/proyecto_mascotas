package com.proyecto_mascotas.beans;

public class Fundacion {
    private int idFundacion;
    private String nombreFundacion;
    private int ubicacion;
    private String telefono;
    private String email;

    public Fundacion(int idFundacion, String nombreFundacion, int ubicacion, String telefono, String email) {
        this.idFundacion = idFundacion;
        this.nombreFundacion = nombreFundacion;
        this.ubicacion = ubicacion;
        this.telefono = telefono;
        this.email = email;
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

    public int getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(int ubicacion) {
        this.ubicacion = ubicacion;
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
                ", ubicacion=" + ubicacion +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
