package com.proyecto_mascotas.beans;

public class Mascota {
    private int idMascota;
    private String nombreMascota;
    private float edad;
    private String descripcion;
    private boolean estado;
    private String especie;
    private String raza;
    private String fundacion;
    private int idRaza;
    private int idFundacion;

    //Para listar en el html
    public Mascota(int idMascota, String nombreMascota, float edad, String descripcion, boolean estado, String especie,
                   String raza, String fundacion) {
        this.idMascota = idMascota;
        this.nombreMascota = nombreMascota;
        this.edad = edad;
        this.descripcion = descripcion;
        this.estado = estado;
        this.especie = especie;
        this.raza = raza;
        this.fundacion = fundacion;
    }

    //Para insertar en la DB
    public Mascota(String nombreMascota, float edad, String descripcion, int idRaza, int idFundacion) {
        this.nombreMascota = nombreMascota;
        this.edad = edad;
        this.descripcion = descripcion;
        this.idRaza = idRaza;
        this.idFundacion = idFundacion;
    }
    public Mascota(int idMascota, String nombreMascota, float edad, String descripcion, boolean estado, int idRaza,
                   int idFundacion) {
        this.idMascota = idMascota;
        this.nombreMascota = nombreMascota;
        this.edad = edad;
        this.descripcion = descripcion;
        this.estado = estado;
        this.idRaza = idRaza;
        this.idFundacion = idFundacion;
    }

    public Mascota(int idMascota, String nombreMascota) {
        this.idMascota = idMascota;
        this.nombreMascota = nombreMascota;
    }

    public int getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(int idMascota) {
        this.idMascota = idMascota;
    }

    public String getNombreMascota() {
        return nombreMascota;
    }

    public void setNombreMascota(String nombreMascota) {
        this.nombreMascota = nombreMascota;
    }

    public float getEdad() {
        return edad;
    }

    public void setEdad(float edad) {
        this.edad = edad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getFundacion() {
        return fundacion;
    }

    public void setFundacion(String fundacion) {
        this.fundacion = fundacion;
    }

    public int getIdRaza() {
        return idRaza;
    }

    public void setIdRaza(int idRaza) {
        this.idRaza = idRaza;
    }

    public int getIdFundacion() {
        return idFundacion;
    }

    public void setIdFundacion(int idFundacion) {
        this.idFundacion = idFundacion;
    }

    @Override
    public String toString() {
        return "Mascota{" +
                "idMascota=" + idMascota +
                ", nombreMascota='" + nombreMascota + '\'' +
                ", edad=" + edad +
                ", descripcion='" + descripcion + '\'' +
                ", estado=" + estado +
                ", especie='" + especie + '\'' +
                ", raza='" + raza + '\'' +
                ", fundacion='" + fundacion + '\'' +
                ", idRaza=" + idRaza +
                ", idFundacion=" + idFundacion +
                '}';
    }
}
