package com.proyecto_mascotas.controller;

import com.google.gson.Gson;
import com.proyecto_mascotas.beans.Adoptante;
import com.proyecto_mascotas.beans.Mascota;
import com.proyecto_mascotas.beans.Usuario;
import com.proyecto_mascotas.connection.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AdoptanteController implements IAdoptanteController{

    @Override
    public String register(long cedula, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, String email, String telefono, String observacion, int idCiudad) {

        Gson gson = new Gson();
        DBConnection conn = new DBConnection();

        String sql = "INSERT INTO adoptante(cedula, primer_nombre, segundo_nombre, primer_apellido, " +
                "segundo_apellido, email, telefono, observacion, id_ciudad) " +
                "VALUES(" + cedula + ", '" + primerNombre +"', '" + segundoNombre + "', '" + primerApellido + "', '" + segundoApellido + "', '" + email + "', '" + telefono + "', '"+ observacion +"', " + idCiudad + ")";

        try {
            Statement stm = conn.getConnection().createStatement();
            stm.executeUpdate(sql);

            Adoptante adoptante = new Adoptante(cedula, primerNombre, segundoNombre, primerApellido, segundoApellido, email, telefono, observacion, idCiudad);

            stm.close();
            return gson.toJson(adoptante);

        } catch (SQLException e) {
            System.out.println(e.getMessage());;
        } finally {
            conn.desconectar();
        }
        return "false";
    }

    @Override
    public String listarAdoptantes() {

        Gson gson = new Gson();
        DBConnection conn = new DBConnection();
        String sql = "SELECT cedula, primer_nombre, segundo_nombre, primer_apellido, segundo_apellido, email, telefono, departamento, ciudad, observacion " +
                "FROM adoptante INNER JOIN ciudad USING(id_ciudad) " +
                "INNER JOIN departamento USING(id_departamento)";

        List<String> adoptantes = new ArrayList<>();

        try {
            Statement stm = conn.getConnection().createStatement();
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                long cedula = rs.getLong("cedula");
                String primerNombre = rs.getString("primer_nombre");
                String segundoNombre = rs.getString("segundo_nombre");
                String primerApellido = rs.getString("primer_apellido");
                String segundoApellido = rs.getString("segundo_apellido");
                String email = rs.getString("email");
                String telefono = rs.getString("telefono");
                String departamento = rs.getString("departamento");
                String ciudad = rs.getString("ciudad");
                String observacion = rs.getString("observacion");

                Adoptante adoptante = new Adoptante(primerNombre, segundoNombre, primerApellido, segundoApellido, email, telefono, ciudad, departamento, cedula, observacion);
                adoptantes.add(gson.toJson(adoptante));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            conn.desconectar();
        }
        return gson.toJson(adoptantes);
    }

    @Override
    public String editarAdoptante(long cedula, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, String email, String telefono, String observacion) {
        DBConnection con = new DBConnection();

        String sql = "UPDATE adoptante SET primer_nombre = '" + primerNombre + "', segundo_nombre = '" + segundoNombre + "', " +
                "primer_apellido = '" + primerApellido + "', segundo_apellido = '" + segundoApellido + "', " +
                "email = '" + email + "', telefono = '" + telefono + "', observacion = '" + observacion + "' " +
                "WHERE cedula = " + cedula;

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
    public String eliminarAdoptante(long cedula) {
        DBConnection con = new DBConnection();

        String sql = "DELETE FROM adoptante WHERE cedula = " + cedula;

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
    public String llenarAdoptanteFrom(long cedula) {
        Gson gson = new Gson();
        DBConnection conn = new DBConnection();
        String sql = "SELECT cedula, primer_nombre, segundo_nombre, primer_apellido, segundo_apellido, email, telefono, departamento, ciudad, observacion " +
                "FROM adoptante INNER JOIN ciudad USING(id_ciudad) " +
                "INNER JOIN departamento USING(id_departamento) " +
                "WHERE cedula = " + cedula;

        try {
            Statement stm = conn.getConnection().createStatement();
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                String primerNombre = rs.getString("primer_nombre");
                String segundoNombre = rs.getString("segundo_nombre");
                String primerApellido = rs.getString("primer_apellido");
                String segundoApellido = rs.getString("segundo_apellido");
                String email = rs.getString("email");
                String telefono = rs.getString("telefono");
                String departamento = rs.getString("departamento");
                String ciudad = rs.getString("ciudad");
                String observacion = rs.getString("observacion");

                Adoptante adoptante = new Adoptante(primerNombre, segundoNombre, primerApellido, segundoApellido, email, telefono, ciudad, departamento, cedula, observacion);
                return gson.toJson(adoptante);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());;
        } finally {
            conn.desconectar();
        }
        return "false";
    }

    @Override
    public String llenarAdoptanteModal(long cedula) {
        Gson gson = new Gson();
        DBConnection conn = new DBConnection();
        String sql = "SELECT CONCAT(primer_apellido, ' ', primer_apellido) AS nombre_completo " +
                "FROM adoptante " +
                "WHERE cedula = " + cedula;

        try {
            Statement stm = conn.getConnection().createStatement();
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                String nombreCompleto = rs.getString("nombre_completo");
                Adoptante adoptante = new Adoptante(cedula, nombreCompleto);
                return gson.toJson(adoptante);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());;
        } finally {
            conn.desconectar();
        }
        return "false";
    }
}

