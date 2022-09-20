package com.proyecto_mascotas.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioControllerTest {

    UsuarioController usuarioController = new UsuarioController();

    @Test
    void login() {
        String userLogin = usuarioController.login("fpantoja", "fpan12345");
        System.out.println("Metodo login:\n\n" + userLogin + "\n\n");
    }

    @Test
    void register() {
        String registroUsuario = usuarioController.register("Test11", "Test10", "Test10", "Test10", "Test10", "test@mail.com", "123456678", "test", 1, 2);
        System.out.println("Metodo register:\n\n" + registroUsuario + "\n\n");
    }

    @Test
    void getUser() {
        String getUser = usuarioController.getUser("fpantoja");
        System.out.println("Metodo getUser:\n\n" + getUser + "\n\n");
    }

    @Test
    void listarUsuarios() {
        String listaUsuarios = usuarioController.listarUsuarios();
        System.out.println("Metodo listarUsuarios:\n\n" + listaUsuarios + "\n\n");
    }
}