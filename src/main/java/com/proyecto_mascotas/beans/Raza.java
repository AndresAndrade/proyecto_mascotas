package com.proyecto_mascotas.beans;

public class Raza {

    private int idRaza;
    private String raza;
    private int idEspecie;

    public Raza(int idRaza, String raza, int idEspecie) {
        this.idRaza = idRaza;
        this.raza = raza;
        this.idEspecie = idEspecie;
    }

    public int getIdRaza() {
        return idRaza;
    }

    public void setIdRaza(int idRaza) {
        this.idRaza = idRaza;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public int getIdEspecie() {
        return idEspecie;
    }

    public void setIdEspecie(int idEspecie) {
        this.idEspecie = idEspecie;
    }

    @Override
    public String toString() {
        return "Raza{" +
                "idRaza=" + idRaza +
                ", raza='" + raza + '\'' +
                ", idEspecie=" + idEspecie +
                '}';
    }
}
