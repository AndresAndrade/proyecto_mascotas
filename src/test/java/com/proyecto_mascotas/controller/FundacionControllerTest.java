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
        String insertarFundacion = fundacionController.registrarFundacion("Test1", "123456", "test10@mail.com", 650);
        System.out.println("Metodo registarFundacion:\n\n" + insertarFundacion + "\n\n");
    }
}