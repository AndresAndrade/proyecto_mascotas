package com.proyecto_mascotas.controller;

import com.google.gson.Gson;
import com.proyecto_mascotas.beans.Ciudad;
import com.proyecto_mascotas.beans.Departamento;
import com.proyecto_mascotas.beans.Usuario;
import com.proyecto_mascotas.connection.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UbicacionController implements IUbicacionController{
    @Override
    public String listarDepartamento() {
        Gson gson = new Gson();
        DBConnection conn = new DBConnection();
        String sql = "SELECT * FROM departamento";

        List<String> departamentos = new ArrayList<>();

        try {
            Statement stm = conn.getConnection().createStatement();
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                int idDepartamento = rs.getInt("id_departamento");
                String departamento = rs.getString("departamento");

                Departamento dpto = new Departamento(idDepartamento, departamento);
                departamentos.add(gson.toJson(dpto));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());;
        } finally {
            conn.desconectar();
        }
        return gson.toJson(departamentos);
    }

    @Override
    public String listarCiudad(String departamento) {
        Gson gson = new Gson();
        DBConnection conn = new DBConnection();
        String sql = "SELECT id_ciudad, ciudad, id_departamento FROM ciudad INNER JOIN departamento USING(id_departamento) WHERE id_departamento = " + departamento + " ORDER BY ciudad";

        List<String> ciudades = new ArrayList<>();

        try {
            Statement stm = conn.getConnection().createStatement();
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                int idCiudad = rs.getInt("id_ciudad");
                String ciudad = rs.getString("ciudad");
                int idDepartamento = rs.getInt("id_departamento");

                Ciudad city = new Ciudad(idCiudad, ciudad, idDepartamento);
                ciudades.add(gson.toJson(city));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());;
        } finally {
            conn.desconectar();
        }
        return gson.toJson(ciudades);
    }
}
