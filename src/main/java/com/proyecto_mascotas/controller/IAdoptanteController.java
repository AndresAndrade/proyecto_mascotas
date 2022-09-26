package com.proyecto_mascotas.controller;

public interface IAdoptanteController {
    String register(long cedula, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, String email, String telefono, String observacion, int idCiudad);
    String listarAdoptantes();
    String editarAdoptante(long cedula, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, String email, String telefono, String observacion);
    String eliminarAdoptante(long cedula);
    String llenarAdoptanteFrom(long cedula);
    String llenarAdoptanteModal(long cedula);
}
