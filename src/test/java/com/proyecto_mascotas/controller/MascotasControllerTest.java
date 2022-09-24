package com.proyecto_mascotas.controller;

import org.junit.jupiter.api.Test;

class MascotasControllerTest {

    MascotasController mascotasController = new MascotasController();

    @Test
    void registerMascota() {
        String registroMascota = mascotasController.registrarMascota("Test11", 8, "Test descripcion10", 1, 1);
        System.out.println("Metodo registerMascota:\n\n" + registroMascota + "\n\n");
    }

    @Test
    void listarMascotas() {
        String listaMascotas = mascotasController.listarMascotas();
        System.out.println("Metodo listarMascotas:\n\n" + listaMascotas + "\n\n");
    }

}