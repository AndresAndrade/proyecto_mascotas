package com.proyecto_mascotas.controller;

public interface IMascotaControlador {
    String listarMascotas();
    String registerMascota(String nombreMascota, float edad, String descripcion, int idRaza, int idFundacion);
}
