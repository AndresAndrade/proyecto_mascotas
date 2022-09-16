package com.proyecto_mascotas.beans;

public class Adoptante extends Persona{
    private long cedula;
    private byte foto; //Corroborar si esto corresponde a una foto
    private String observacion;

    public Adoptante(long cedula, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, String email, String telefono, int ubicacion, byte foto, String observacion) {
        super(primerNombre, segundoNombre, primerApellido, segundoApellido, email, telefono, ubicacion);
        this.cedula = cedula;
        this.foto = foto;
        this.observacion = observacion;
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
                "} " + super.toString();
    }
}
