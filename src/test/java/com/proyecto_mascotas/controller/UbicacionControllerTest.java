package com.proyecto_mascotas.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UbicacionControllerTest {

    UbicacionController ubicacionController = new UbicacionController();

    @Test
    void listarDepartamento() {
        String listaDepartamentos = ubicacionController.listarDepartamento();
        System.out.println("Metodo listarDepartamento:\n\n" + listaDepartamentos + "\n\n");
    }

    @Test
    void listarCiudad() {
        String listaCiudades = ubicacionController.listarCiudad("Nariño");
        System.out.println("Metodo listarCiudad:\n\n" + listaCiudades + "\n\n");
    }
}