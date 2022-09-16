package com.proyecto_mascotas.beans;

public class Adopcion {
    private long idAdopcion;
    private long cedula;
    private int idMascota;

    public Adopcion(long idAdopcion, long cedula, int idMascota) {
        this.idAdopcion = idAdopcion;
        this.cedula = cedula;
        this.idMascota = idMascota;
    }

    public long getIdAdopcion() {
        return idAdopcion;
    }

    public void setIdAdopcion(long idAdopcion) {
        this.idAdopcion = idAdopcion;
    }

    public long getCedula() {
        return cedula;
    }

    public void setCedula(long cedula) {
        this.cedula = cedula;
    }

    public long getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(int idMascota) {
        this.idMascota = idMascota;
    }

    @Override
    public String toString() {
        return "Adopcion{" +
                "idAdopcion=" + idAdopcion +
                ", cedula=" + cedula +
                ", idMascota=" + idMascota +
                '}';
    }
}
