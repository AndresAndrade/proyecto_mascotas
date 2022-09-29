package com.proyecto_mascotas.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdoptanteControllerTest {

    AdoptanteController adoptanteController = new AdoptanteController();

    @Test
    void register() {
        String insertAdoptante = adoptanteController.register(15935441, "Roberto", "Carlos",
                "Suarez", "Castillo", "rsuarez0@mail.com", "31574259637", "", 1092);
        System.out.println("Metodo register:\n\n" + insertAdoptante + "\n\n");
    }

    @Test
    void listarAdoptantes() {
        String listaAdoptantes = adoptanteController.listarAdoptantes();
        System.out.println("Metodo listarAdoptantes:\n\n" + listaAdoptantes + "\n\n");
    }

    @Test
    void editarAdoptante() {
        String editarAdoptante = adoptanteController.editarAdoptante(15935441, "Roberto", "Fernando",
                "Suarez", "Paredes", "rfsuarez0@mail.com", "31574259637", "");
        System.out.println("Metodo editarAdoptante:\n\n" + editarAdoptante + "\n\n");
    }

    @Test
    void llenarAdoptanteForm() {
        String llenarAdoptante = adoptanteController.llenarAdoptanteForm(15935441);
        System.out.println("Metodo llenarAdoptanteForm:\n\n" + llenarAdoptante + "\n\n");
    }

    @Test
    void llenarAdoptanteModal() {
        String llenarAdoptante = adoptanteController.llenarAdoptanteModal(15935441);
        System.out.println("Metodo llenarAdoptanteModal:\n\n" + llenarAdoptante + "\n\n");
    }

    @Test
    void eliminarAdoptante() {
        String eliminarAdoptante = adoptanteController.eliminarAdoptante(15935441);
        System.out.println("Metodo eliminarAdoptante:\n\n" + eliminarAdoptante + "\n\n");
    }
}