package com.proyecto_mascotas.beans;

public class Ciudad {
    int idCiudad;
    String ciudad;
    int idDepartamento;

    public Ciudad(int idCiudad, String ciudad, int idDepartamento) {
        this.idCiudad = idCiudad;
        this.ciudad = ciudad;
        this.idDepartamento = idDepartamento;
    }

    public int getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(int idCiudad) {
        this.idCiudad = idCiudad;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public int getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    @Override
    public String toString() {
        return "Ciudad{" +
                "idCiudad=" + idCiudad +
                ", ciudad='" + ciudad + '\'' +
                ", idDepartamento=" + idDepartamento +
                '}';
    }
}
