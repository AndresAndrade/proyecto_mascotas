package com.proyecto_mascotas.beans;

public class Adoptante extends Persona{
    private long cedula;
    private String observacion;
    private String nombreCompleto;


    /*Para insertar un registro en la DB*/
    public Adoptante(long cedula, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido,
                     String email, String telefono, String observacion, int idCiudad) {
        super(primerNombre, segundoNombre, primerApellido, segundoApellido, email, telefono, idCiudad);
        this.cedula = cedula;
        this.observacion = observacion;
    }

    /*Para listar en el html*/
    public Adoptante(String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, String email,
                     String telefono, String ciudad, String departamento, long cedula, String observacion) {
        super(primerNombre, segundoNombre, primerApellido, segundoApellido, email, telefono, ciudad, departamento);
        this.cedula = cedula;
        this.observacion = observacion;
    }

    public Adoptante(long cedula, String nombreCompleto) {
        super();
        this.cedula = cedula;
        this.nombreCompleto = nombreCompleto;
    }

    public long getCedula() {
        return cedula;
    }

    public void setCedula(long cedula) {
        this.cedula = cedula;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    @Override
    public String toString() {
        return "Adoptante{" +
                "cedula=" + cedula +
                ", observacion='" + observacion + '\'' +
                "} " + super.toString();
    }
}
