package com.proyecto_mascotas.controller;

import com.google.gson.Gson;
import com.proyecto_mascotas.beans.Mascota;
import com.proyecto_mascotas.beans.Usuario;
import com.proyecto_mascotas.connection.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
                int idCiudad = rs.getInt("id_ciudad");
                int idFundacion = rs.getInt("id_fundacion");

                Usuario usuario = new Usuario(idUsuario, username, primerNombre, segundoNombre, primerApellido, segundoApellido, email, telefono, password, idCiudad, idFundacion);
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
    public String register(String username, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, String email, String telefono, String password, int idCiudad, int idFundacion) {

        Gson gson = new Gson();
        DBConnection conn = new DBConnection();

        String sql = "INSERT INTO usuario(username, primer_nombre, segundo_nombre, primer_apellido, " +
                "segundo_apellido, email, telefono, password, id_ciudad, id_fundacion) " +
                "VALUES('" + username + "', '" + primerNombre +"', '" + segundoNombre + "', '" + primerApellido + "', '" + segundoApellido + "', '" + email + "', '" + telefono + "', '"+ password +"', " + idCiudad + ", " + idFundacion + ")";
        
        try {
            Statement stm = conn.getConnection().createStatement();
            stm.executeUpdate(sql);

            Usuario usuario = new Usuario(username, primerNombre, segundoNombre, primerApellido, segundoApellido, email, telefono, password, idCiudad, idFundacion);

            stm.close();
            return gson.toJson(usuario);

        } catch (SQLException e) {
            System.out.println(e.getMessage());;
        } finally {
            conn.desconectar();
        }
        return "false";
    }

    @Override
    public String getUser(String username) {
        Gson gson = new Gson();
        DBConnection conn = new DBConnection();
        String sql = "SELECT * FROM usuario WHERE username = '" + username + "'";

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
                String password = rs.getString("password");
                int idCiudad = rs.getInt("id_ciudad");
                int idFundacion = rs.getInt("id_fundacion");

                Usuario usuario = new Usuario(idUsuario, username, primerNombre, segundoNombre, primerApellido, segundoApellido, email, telefono, password, idCiudad, idFundacion);
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
    public String listarUsuarios() {
        Gson gson = new Gson();
        DBConnection conn = new DBConnection();
        String sql = "SELECT id_usuario, primer_nombre, segundo_nombre, primer_apellido, segundo_apellido, usuario.email, usuario.telefono, fundacion.nombre, usuario.id_ciudad " +
                "FROM usuario INNER JOIN fundacion USING(id_fundacion) " +
                "ORDER BY id_usuario";

        List<String> usuarios = new ArrayList<>();

        try {
            Statement stm = conn.getConnection().createStatement();
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                int idUsuario = rs.getInt("id_usuario");
                String primerNombre = rs.getString("primer_nombre");
                String segundoNombre = rs.getString("segundo_nombre");
                String primerApellido = rs.getString("primer_apellido");
                String segundoApellido = rs.getString("segundo_apellido");
                String email = rs.getString("usuario.email");
                String telefono = rs.getString("usuario.telefono");
                int idCiudad = rs.getInt("usuario.id_ciudad");
                String fundacion = rs.getString("fundacion.nombre");

                Usuario usuario = new Usuario(idUsuario, primerNombre, segundoNombre, primerApellido, segundoApellido, email, telefono, idCiudad, fundacion);
                usuarios.add(gson.toJson(usuario));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());;
        } finally {
            conn.desconectar();
        }
        return gson.toJson(usuarios);
    }
}

