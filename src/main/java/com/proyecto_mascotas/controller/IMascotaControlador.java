package com.proyecto_mascotas.controller;

public interface IMascotaControlador {
    String listarMascotas();
    String registrarMascota(String nombreMascota, float edad, String descripcion, int idRaza, int idFundacion);
    String editarMascota(int idMascota, String nombreMascota, float edad, String descripcion, boolean estado);
    String eliminarMascota(int idMascota);
    String llenarMascotaForm(int idMascota);
    String llenarMascotaModal(int idMascota);
}
