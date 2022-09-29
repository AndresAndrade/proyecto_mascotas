package com.proyecto_mascotas.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MascotasControllerTest {

    MascotasController mascotasController = new MascotasController();
    @Test
    void registerMascota() {
        String registroMascota = mascotasController.registrarMascota("Ponquesito", 8,
                "Sociable con animales y niños", 6, 1);
        System.out.println("Metodo registerMascota:\n\n" + registroMascota + "\n\n");
    }

    @Test
    void listarMascotas() {
        String listaMascotas = mascotasController.listarMascotas();
        System.out.println("Metodo listarMascotas:\n\n" + listaMascotas + "\n\n");
    }

    @Test
    void editarMascota() {
        String editarMascota = mascotasController.editarMascota(34, "Rex", 10,
                "En adaptación", true);
        System.out.println("Metodo editarMascota:\n\n" + editarMascota + "\n\n");
    }

    @Test
    void llenarMascotaForm() {
        String llenarMascota = mascotasController.llenarMascotaForm(34);
        System.out.println("Metodo llenarMascotaForm:\n\n" + llenarMascota + "\n\n");
    }

    @Test
    void llenarMascotaModal() {
        String llenarMascota = mascotasController.llenarMascotaModal(34);
        System.out.println("Metodo llenarMascotaModal:\n\n" + llenarMascota + "\n\n");
    }

    @Test
    void eliminarMascota() {
        String eliminarMascota = mascotasController.eliminarMascota(34);
        System.out.println("Metodo eliminarMascota:\n\n" + eliminarMascota + "\n\n");
    }
}