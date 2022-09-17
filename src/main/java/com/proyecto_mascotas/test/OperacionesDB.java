package com.proyecto_mascotas.test;

import com.google.gson.Gson;
import com.proyecto_mascotas.beans.Mascota;
import com.proyecto_mascotas.beans.Usuario;
import com.proyecto_mascotas.connection.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OperacionesDB {
    public static void main(String[] args) throws SQLException {
        listarMascotas();
        //listarUsuarios("fpantoja", "fpan12345");
        //actualizarMascota(13, "Wasabi");
    }

    public static void actualizarMascota(long id, String nombre) throws SQLException {
        DBConnection conn = new DBConnection();
        String sql = "UPDATE mascota SET nombre_mascota = '" + nombre + "' WHERE id_mascota = " + id;

        try {
            Statement stm = conn.getConnection().createStatement();
            stm.executeUpdate(sql);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } finally {
            conn.desconectar();
        }
    }

    public static void listarMascotas() throws SQLException {
        DBConnection conn = new DBConnection();
        String sql = "SELECT id_mascota, nombre_mascota, edad, especie, raza, fundacion.nombre, foto, estado, descripcion " +
                "FROM mascota INNER JOIN raza USING(id_raza) " +
                "INNER JOIN especie USING(id_especie) " +
                "INNER JOIN fundacion USING(id_fundacion)";

        try {
            Statement stm = conn.getConnection().createStatement();
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {

                int idMascota = rs.getInt("id_mascota");
                String nombreMascota = rs.getString("nombre_mascota");
                float edad = rs.getFloat("edad");
                String descripcion = rs.getString("descripcion");
                boolean estado = rs.getBoolean("estado");
                byte foto = rs.getByte("foto");
                String especie = rs.getString("especie");
                String raza = rs.getString("raza");
                String fundacion = rs.getString("fundacion.nombre");

                Mascota mascota = new Mascota(idMascota, nombreMascota, edad, descripcion, estado, foto, especie, raza, fundacion);
                System.out.println(mascota);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } finally {
            conn.desconectar();
        }


    }

    public static void listarUsuarios(String username, String password) throws SQLException {
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
                int idUbicacion = rs.getInt("id_ciudad");
                int idFundacion = rs.getInt("id_fundacion");

                Usuario usuario = new Usuario(idUsuario, username, primerNombre, segundoNombre, primerApellido, segundoApellido, email, telefono, password, idUbicacion, idFundacion);
                System.out.println(gson.toJson(usuario));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } finally {
            conn.desconectar();
        }

        System.out.println("false");
    }
}
