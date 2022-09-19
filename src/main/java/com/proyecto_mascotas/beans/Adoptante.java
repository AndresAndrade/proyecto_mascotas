package com.proyecto_mascotas.beans;

public class Adoptante extends Persona{
    private long cedula;
    private byte foto; //Corroborar si esto corresponde a una foto
    private String observacion;
    private String ciudad;

    public Adoptante(String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, String email, String telefono, int idCiudad, long cedula, byte foto, String observacion) {
        super(primerNombre, segundoNombre, primerApellido, segundoApellido, email, telefono, idCiudad);
        this.cedula = cedula;
        this.foto = foto;
        this.observacion = observacion;
    }

    /*Para insertar un registro en la DB*/
    public Adoptante(long cedula, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, String email, String telefono, String observacion, int idCiudad) {
        super(primerNombre, segundoNombre, primerApellido, segundoApellido, email, telefono, idCiudad);
        this.cedula = cedula;
        this.observacion = observacion;
    }

    /*Para listar en el html*/
    public Adoptante(long cedula, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, String email, String telefono, int idCiudad, String ciudad,  String observacion) {
        super(primerNombre, segundoNombre, primerApellido, segundoApellido, email, telefono, idCiudad);
        this.cedula = cedula;
        this.observacion = observacion;
        this.ciudad = ciudad;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public long getCedula() {
        return cedula;
    }

    public void setCedula(long cedula) {
        this.cedula = cedula;
    }

    public byte getFoto() {
        return foto;
    }

    public void setFoto(byte foto) {
        this.foto = foto;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    @Override
    public String toString() {
        return "Adoptante{" +
                "cedula=" + cedula +
                ", foto=" + foto +
                ", observacion='" + observacion + '\'' +
                ", ciudad='" + ciudad + '\'' +
                "} " + super.toString();
    }
}
