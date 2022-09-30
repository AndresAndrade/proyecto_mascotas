package com.proyecto_mascotas.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioControllerTest {

    UsuarioController usuarioController = new UsuarioController();

    @Test
    void login() {
        String userLogin = usuarioController.login("admin", "admin123");
        System.out.println("Metodo login:\n\n" + userLogin + "\n\n");
    }

    @Test
    void register() {
        String registroUsuario = usuarioController.register("arosero", "Alex",
                "Fernando", "Rosero", "Guerrero", "arosero@mail.com",
                "3145263852", "arosero123", 770, 1);
        System.out.println("Metodo register:\n\n" + registroUsuario + "\n\n");
    }

    @Test
    void getUser() {
        String getUser = usuarioController.getUser("arosero");
        System.out.println("Metodo getUser:\n\n" + getUser + "\n\n");
    }

    @Test
    void listarUsuarios() {
        String listaUsuarios = usuarioController.listarUsuarios();
        System.out.println("Metodo listarUsuarios:\n\n" + listaUsuarios + "\n\n");
    }

    @Test
    void editarUsuario() {
        String editarUsuario = usuarioController.editarUsuario(20, "Alejandro",
                "Fernando", "Rosero", "Guerra", "aroserog@mail.com",
                "3145263852", "aroserog123");
        System.out.println("Metodo editarUsuario:\n\n" + editarUsuario + "\n\n");
    }

    @Test
    void llenarUsuarioForm() {
        String llenarUsuario = usuarioController.llenarUsuarioForm(20);
        System.out.println("Metodo llenarUsuarioForm:\n\n" + llenarUsuario + "\n\n");
    }

    @Test
    void llenarUsuarioModal() {
        String llenarUsuario = usuarioController.llenarUsuarioModal(20);
        System.out.println("Metodo llenarUsuarioModal:\n\n" + llenarUsuario + "\n\n");
    }

    @Test
    void eliminarUsuario() {
        String eliminarUsuario = usuarioController.eliminarUsuario(20);
        System.out.println("Metodo eliminarUsuario:\n\n" + eliminarUsuario + "\n\n");
    }
}