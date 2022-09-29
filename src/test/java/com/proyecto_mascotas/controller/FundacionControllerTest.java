package com.proyecto_mascotas.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FundacionControllerTest {

    FundacionController fundacionController = new FundacionController();

    @Test
    void seleccionarFundacion() {
        String listaFundaciones = fundacionController.seleccionarFundacion();
        System.out.println("Metodo seleccionarFundacion:\n\n" + listaFundaciones + "\n\n");
    }

    @Test
    void listarFundacion() {
        String listaFundaciones = fundacionController.listarFundacion();
        System.out.println("Metodo listarFundacion:\n\n" + listaFundaciones + "\n\n");
    }

    @Test
    void registrarFundacion() {
        String insertarFundacion = fundacionController.registrarFundacion("Granja animal", "3175246824",
                "ganimal@mail.com", 1092);
        System.out.println("Metodo registarFundacion:\n\n" + insertarFundacion + "\n\n");
    }
    @Test
    void editarFundacion() {
        String editarFundacion = fundacionController.editarFundacion(10, "Granja animal", "3175246824",
                "ganimal@mail.com");
        System.out.println("Metodo editarFundacion:\n\n" + editarFundacion + "\n\n");
    }

    @Test
    void llenarFundacionForm() {
        String llenarFundacion = fundacionController.llenarFundacionForm(10);
        System.out.println("Metodo llenarFundacionForm:\n\n" + llenarFundacion + "\n\n");
    }

    @Test
    void llenarFundacionModal() {
        String llenarFundacion = fundacionController.llenarFundacionModal(10);
        System.out.println("Metodo llenarFundacionModal:\n\n" + llenarFundacion + "\n\n");
    }

    @Test
    void eliminarFundacion() {
        String eliminarFundacion = fundacionController.eliminarFundacion(10);
        System.out.println("Metodo eliminarFundacion:\n\n" + eliminarFundacion + "\n\n");
    }
}