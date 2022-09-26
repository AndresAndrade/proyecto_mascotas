package com.proyecto_mascotas.controller;

public interface IFundacionController {
    String seleccionarFundacion();
    String listarFundacion();
    String registrarFundacion(String nombreFundacion, String telefono, String email, int idCiudad);
    String editarFundacion(int idFundacion, String nombreFundacion, String telefono, String email);
    String eliminarFundacion(int idFundacion);
    String llenarFundacionForm(int idFundacion);
    String llenarFundacionModal(int idFundacion);
}
