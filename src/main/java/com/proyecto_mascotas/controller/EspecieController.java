package com.proyecto_mascotas.controller;

import com.google.gson.Gson;
import com.proyecto_mascotas.beans.Especie;
import com.proyecto_mascotas.beans.Raza;
import com.proyecto_mascotas.connection.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EspecieController implements IEspecieController{
    @Override
    public String listarEspecie() {
        Gson gson = new Gson();
        DBConnection conn = new DBConnection();
        String sql = "SELECT * FROM especie";

        List<String> especies = new ArrayList<>();

        try {
            Statement stm = conn.getConnection().createStatement();
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                int idEspecie = rs.getInt("id_especie");
                String especie = rs.getString("especie");

                 Especie specie = new Especie(idEspecie, especie);
                especies.add(gson.toJson(specie));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());;
        } finally {
            conn.desconectar();
        }
        return gson.toJson(especies);
    }

    @Override
    public String listarRaza(String especie) {
        Gson gson = new Gson();
        DBConnection conn = new DBConnection();
        String sql = "SELECT id_raza, raza, id_especie FROM raza INNER JOIN especie USING(id_especie) WHERE especie = '"
                + especie + "' ORDER BY raza";

        List<String> razas = new ArrayList<>();

        try {
            Statement stm = conn.getConnection().createStatement();
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                int idRaza = rs.getInt("id_raza");
                String raza = rs.getString("raza");
                int idEspecie = rs.getInt("id_especie");

                Raza race = new Raza(idRaza, raza, idEspecie);
                razas.add(gson.toJson(race));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());;
        } finally {
            conn.desconectar();
        }
        return gson.toJson(razas);
    }
}
