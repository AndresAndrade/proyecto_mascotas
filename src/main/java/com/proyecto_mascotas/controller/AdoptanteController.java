package com.proyecto_mascotas.controller;

import com.google.gson.Gson;
import com.proyecto_mascotas.beans.Adoptante;
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
        String sql = "SELECT cedula, primer_nombre, segundo_nombre, primer_apellido, segundo_apellido, email, telefono, adoptante.id_ciudad, ciudad, observacion " +
                "FROM adoptante INNER JOIN ciudad USING(id_ciudad)";

        List<String> adoptantes = new ArrayList<>();

        try {
            Statement stm = conn.getConnection().createStatement();
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                long idUsuario = rs.getLong("cedula");
                String primerNombre = rs.getString("primer_nombre");
                String segundoNombre = rs.getString("segundo_nombre");
                String primerApellido = rs.getString("primer_apellido");
                String segundoApellido = rs.getString("segundo_apellido");
                String email = rs.getString("email");
                String telefono = rs.getString("telefono");
                int idCiudad = rs.getInt("adoptante.id_ciudad");
                String ciudad = rs.getString("ciudad");
                String observacion = rs.getString("observacion");

                Adoptante adoptante = new Adoptante(idUsuario, primerNombre, segundoNombre, primerApellido, segundoApellido, email, telefono, idCiudad, ciudad, observacion);
                adoptantes.add(gson.toJson(adoptante));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());;
        } finally {
            conn.desconectar();
        }
        return gson.toJson(adoptantes);
    }
}
