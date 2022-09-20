package com.proyecto_mascotas.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdoptanteControllerTest {

    AdoptanteController adoptanteController = new AdoptanteController();

    @Test
    void register() {
        String insertAdoptante = adoptanteController.register(15935441, "Test10", "Test10", "Test10", "Test10", "test10@mail.com", "75321597", "Test observacion", 2);
        System.out.println("Metodo register:\n\n" + insertAdoptante + "\n\n");
    }

    @Test
    void listarAdoptantes() {
        String listaAdoptantes = adoptanteController.listarAdoptantes();
        System.out.println("Metodo listarAdoptantes:\n\n" + listaAdoptantes + "\n\n");
    }
}