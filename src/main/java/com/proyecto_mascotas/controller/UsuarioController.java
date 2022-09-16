package com.proyecto_mascotas.controller;

import com.google.gson.Gson;
import com.proyecto_mascotas.beans.Usuario;
import com.proyecto_mascotas.connection.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UsuarioController implements IUsuarioController{

    @Override
    public String login(String username, String password) {
        Gson gson = new Gson();
        DBConnection conn = new DBConnection();
        String sql = "SELECT * FROM usuario WHERE username = '" + username + "' AND password = '" + password + "'";

        try {
            Statement stm = conn.getConnection().createStatement();
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                int idUsuario = rs.getInt("id_usuario");
                String primerNombre = rs.getString("primer_nombre");
                String segundoNombre = rs.getString("segundo_nombre");
                String primerApellido = rs.getString("primer_apellido");
                String segundoApellido = rs.getString("segundo_apellido");
                String email = rs.getString("email");
                String telefono = rs.getString("telefono");
                int idUbicacion = rs.getInt("id_ubicacion");
                int idFundacion = rs.getInt("id_fundacion");

                Usuario usuario = new Usuario(idUsuario, username, primerNombre, segundoNombre, primerApellido, segundoApellido, email, telefono, password, idUbicacion, idFundacion);
                return gson.toJson(usuario);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());;
        } finally {
            conn.desconectar();
        }
        return "false";
    }

    @Override
    public String register(String username, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, String email, String telefono, String password, int idUbicacion, int idFundacion) {
        Gson gson = new Gson();
        DBConnection conn = new DBConnection();
        String sql = "INSERT INTO usuarios VALUES('" + username + "', '" + primerNombre +"', '" + segundoNombre + "', '" + primerApellido + "', '" + segundoApellido + "', '" + email + "', '" + telefono + "', '"+ password +"', " + idUbicacion + ", " + idFundacion + ")";

        String consultaId = "SELECT id_usuario FROM usuarios WHERE username = " + username;

        try {
            Statement stm = conn.getConnection().createStatement();
            stm.executeUpdate(sql);
            ResultSet rs = stm.executeQuery(consultaId);

            int idUsuario = rs.getInt("id_usuario");

            Usuario usuario = new Usuario(idUsuario, username, primerNombre, segundoNombre, primerApellido, segundoApellido, email, telefono, password, idUbicacion, idFundacion);

            stm.close();
            return gson.toJson(usuario);

        } catch (SQLException e) {
            System.out.println(e.getMessage());;
        } finally {
            conn.desconectar();
        }
        return "false";
    }
}

