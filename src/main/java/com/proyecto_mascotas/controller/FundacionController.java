package com.proyecto_mascotas.controller;

import com.google.gson.Gson;
import com.proyecto_mascotas.beans.Fundacion;
import com.proyecto_mascotas.connection.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FundacionController implements IFundacionController{
    @Override
    public String seleccionarFundacion() {
        Gson gson = new Gson();
        DBConnection conn = new DBConnection();
        String sql = "SELECT * FROM fundacion";

        List<String> fundaciones = new ArrayList<>();

        try {
            Statement stm = conn.getConnection().createStatement();
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                int idFundacion = rs.getInt("id_fundacion");
                String nombre = rs.getString("nombre");
                String telefono = rs.getString("telefono");
                String email = rs.getString("email");
                int idCiudad = rs.getInt("id_ciudad");

                Fundacion fundacion = new Fundacion(idFundacion, nombre, telefono, email, idCiudad);
                fundaciones.add(gson.toJson(fundacion));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());;
        } finally {
            conn.desconectar();
        }
        return gson.toJson(fundaciones);
    }

    @Override
    public String listarFundacion() {
        Gson gson = new Gson();
        DBConnection conn = new DBConnection();
        String sql = "SELECT id_fundacion, nombre, telefono, email, ciudad, departamento " +
                "FROM fundacion " +
                "INNER JOIN ciudad USING(id_ciudad) " +
                "INNER JOIN departamento USING(id_departamento) " +
                "ORDER BY id_fundacion";

        List<String> fundaciones = new ArrayList<>();

        try {
            Statement stm = conn.getConnection().createStatement();
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                int idFundacion = rs.getInt("id_fundacion");
                String nombre = rs.getString("nombre");
                String telefono = rs.getString("telefono");
                String email = rs.getString("email");
                String ciudad = rs.getString("ciudad.ciudad");
                String departamento = rs.getString("departamento.departamento");

                Fundacion fundacion = new Fundacion(idFundacion, nombre, telefono, email, ciudad, departamento);
                fundaciones.add(gson.toJson(fundacion));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());;
        } finally {
            conn.desconectar();
        }
        return gson.toJson(fundaciones);
    }

    @Override
    public String registrarFundacion(String nombreFundacion, String telefono, String email, int idCiudad) {

        Gson gson = new Gson();
        DBConnection conn = new DBConnection();

        String sql = "INSERT INTO fundacion(nombre, telefono, email, id_ciudad) " +
                "VALUES('" + nombreFundacion + "', '" + telefono +"', '" + email + "', " + idCiudad + ")";

        try {
            Statement stm = conn.getConnection().createStatement();
            stm.executeUpdate(sql);

            Fundacion fundacion = new Fundacion(nombreFundacion, telefono, email, idCiudad);

            stm.close();
            return gson.toJson(fundacion);

        } catch (SQLException e) {
            System.out.println(e.getMessage());;
        } finally {
            conn.desconectar();
        }
        return "false";
    }
}

