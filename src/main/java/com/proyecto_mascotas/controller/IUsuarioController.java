package com.proyecto_mascotas.controller;

public interface IUsuarioController {
    String login(String username, String password);
    String register(String username, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, String email, String telefono, String password, int idCiudad, int idFundacion);
    String getUser(String username);
    String listarUsuarios();
    String editarusuario(int idUsuario, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, String email, String telefono, String password);
    String eliminarUsuario(int idUsuario);
    String llenarUsuarioFrom(int idUsuario);
}
