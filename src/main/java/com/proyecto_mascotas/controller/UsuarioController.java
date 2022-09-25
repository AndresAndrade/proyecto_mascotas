package com.proyecto_mascotas.controller;

import com.google.gson.Gson;
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

    @Override
    public String editarusuario(int idUsuario, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, String email, String telefono, String password) {
        DBConnection con = new DBConnection();

        String sql = "UPDATE usuario SET primer_nombre = '" + primerNombre + "', segundo_nombre = '" + segundoNombre + "', " +
                "primer_apellido = '" + primerApellido + "', segundo_apellido = '" + segundoApellido + "', " +
                "email = '" + email + "', telefono = '" + telefono + "', password = '" + password + "' " +
                "WHERE id_usuario = " + idUsuario;

        try {
            Statement st = con.getConnection().createStatement();
            st.executeUpdate(sql);
            return "true";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            con.desconectar();
        }
        return "false";
    }

    @Override
    public String eliminarUsuario(int idUsuario) {
        return null;
    }

    @Override
    public String llenarUsuarioFrom(int idUsuario) {
        Gson gson = new Gson();
        DBConnection conn = new DBConnection();
        String sql = "SELECT username, password, primer_nombre, segundo_nombre, primer_apellido, segundo_apellido, usuario.email, usuario.telefono, fundacion.nombre, ciudad, departamento " +
                "FROM usuario INNER JOIN fundacion USING(id_fundacion) " +
                "INNER JOIN ciudad ON usuario.id_ciudad = ciudad.id_ciudad " +
                "INNER JOIN departamento USING(id_departamento) " +
                "WHERE id_usuario = " + idUsuario;

        try {
            Statement stm = conn.getConnection().createStatement();
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                String primerNombre = rs.getString("primer_nombre");
                String segundoNombre = rs.getString("segundo_nombre");
                String primerApellido = rs.getString("primer_apellido");
                String segundoApellido = rs.getString("segundo_apellido");
                String email = rs.getString("usuario.email");
                String telefono = rs.getString("usuario.telefono");
                String ciudad = rs.getString("ciudad");
                String departamento = rs.getString("departamento");
                String fundacion = rs.getString("fundacion.nombre");

                Usuario usuario = new Usuario(primerNombre, segundoNombre, primerApellido, segundoApellido, email, telefono, ciudad, departamento, idUsuario, username, password, fundacion);
                return gson.toJson(usuario);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());;
        } finally {
            conn.desconectar();
        }
        return "false";
    }
}

