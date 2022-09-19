package com.proyecto_mascotas.controller;

public interface IFundacionController {
    String seleccionarFundacion();
    String listarFundacion();
    String registrarFundacion(String nombreFundacion, String telefono, String email, int idCiudad);
}
