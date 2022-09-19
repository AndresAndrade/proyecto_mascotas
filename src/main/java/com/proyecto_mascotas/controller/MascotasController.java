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

public class MascotasController implements IMascotaControlador{
    @Override
    public String listarMascotas() {
        Gson gson = new Gson();
        DBConnection conn = new DBConnection();
        String sql = "SELECT id_mascota, nombre_mascota, edad, especie, raza, fundacion.nombre, foto, estado, descripcion " +
                "FROM mascota INNER JOIN raza USING(id_raza) " +
                "INNER JOIN especie USING(id_especie) " +
                "INNER JOIN fundacion USING(id_fundacion) ORDER BY id_mascota";

        List<String> mascotas = new ArrayList<>();

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
                mascotas.add(gson.toJson(mascota));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());;
        } finally {
            conn.desconectar();
        }
        return gson.toJson(mascotas);
    }

    @Override
    public String registerMascota(String nombreMascota, float edad, String descripcion, int idRaza, int idFundacion) {

        Gson gson = new Gson();
        DBConnection conn = new DBConnection();

        String sql = "INSERT INTO mascota(nombre_mascota, edad, descripcion, id_raza, id_fundacion) " +
                "VALUES('" + nombreMascota + "', " + edad +", '" + descripcion + "', " + idRaza + ", " + idFundacion + ")";

        try {
            Statement stm = conn.getConnection().createStatement();
            stm.executeUpdate(sql);

            Mascota mascota = new Mascota(nombreMascota, edad, descripcion, idRaza, idFundacion);

            stm.close();
            return gson.toJson(mascota);

        } catch (SQLException e) {
            System.out.println(e.getMessage());;
        } finally {
            conn.desconectar();
        }
        return "false";
    }
}
