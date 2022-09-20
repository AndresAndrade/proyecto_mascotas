package com.proyecto_mascotas.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EspecieControllerTest {

    EspecieController especieController = new EspecieController();

    @Test
    void listarEspecie() {
        String listaEspecies = especieController.listarEspecie();
        System.out.println("Metodo listarEspecie:\n\n" + listaEspecies + "\n\n");
    }

    @Test
    void listarRaza() {
        String listaRazas = especieController.listarRaza("Perro");
        System.out.println("Metodo listarRaza:\n\n" + listaRazas);
    }
}