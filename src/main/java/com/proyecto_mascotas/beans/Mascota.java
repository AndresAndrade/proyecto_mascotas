package com.proyecto_mascotas.beans;

public class Mascota {
    private int idMascota;
    private String nombreMascota;
    private float edad;
    private String descripcion;
    private boolean estado;
    private byte foto; //Verificar si es correcto el tipo para la foto
    private int idEspecie;
    private int idFundacion;

    public Mascota(int idMascota, String nombreMascota, float edad, String descripcion, boolean estado, byte foto, int idEspecie, int idFundacion) {
        this.idMascota = idMascota;
        this.nombreMascota = nombreMascota;
        this.edad = edad;
        this.descripcion = descripcion;
        this.estado = estado;
        this.foto = foto;
        this.idEspecie = idEspecie;
        this.idFundacion = idFundacion;
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

    public byte getFoto() {
        return foto;
    }

    public void setFoto(byte foto) {
        this.foto = foto;
    }

    public int getIdEspecie() {
        return idEspecie;
    }

    public void setIdEspecie(int idEspecie) {
        this.idEspecie = idEspecie;
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
                ", foto=" + foto +
                ", idEspecie=" + idEspecie +
                ", idFundacion=" + idFundacion +
                '}';
    }
}
