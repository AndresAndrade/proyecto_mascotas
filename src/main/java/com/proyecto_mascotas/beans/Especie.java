package com.proyecto_mascotas.beans;

public class Especie {
    private int idEspecie;
    private String especie;

    public Especie(int idEspecie, String especie) {
        this.idEspecie = idEspecie;
        this.especie = especie;
    }

    public int getIdEspecie() {
        return idEspecie;
    }

    public void setIdEspecie(int idEspecie) {
        this.idEspecie = idEspecie;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    @Override
    public String toString() {
        return "Especie{" +
                "idEspecie=" + idEspecie +
                ", especie='" + especie + '\'' +
                '}';
    }
}
