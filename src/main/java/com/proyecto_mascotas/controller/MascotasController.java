package com.proyecto_mascotas.controller;

import com.google.gson.Gson;
import com.proyecto_mascotas.beans.Mascota;
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
        String sql = "SELECT id_mascota, nombre_mascota, edad, especie, raza, fundacion.nombre, estado, descripcion " +
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
                String especie = rs.getString("especie");
                String raza = rs.getString("raza");
                String fundacion = rs.getString("fundacion.nombre");

                Mascota mascota = new Mascota(idMascota, nombreMascota, edad, descripcion, estado, especie, raza, fundacion);
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
    public String registrarMascota(String nombreMascota, float edad, String descripcion, int idRaza, int idFundacion) {

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

    @Override
    public String editarMascota(int idMascota, String nombreMascota, float edad, String descripcion, boolean estado) {
        DBConnection con = new DBConnection();

        String sql = "UPDATE mascota SET nombre_mascota = '" + nombreMascota + "', edad = " + edad + ", descripcion = '"
                + descripcion + "', estado = ";

        if (estado == true) {
            sql += " 1 ";
        } else {
            sql += " 0 ";
        }

        sql += " WHERE id_mascota = " + idMascota;

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
    public String eliminarMascota(int idMascota) {
        DBConnection con = new DBConnection();

        String sql = "DELETE FROM mascota WHERE id_mascota = " + idMascota;

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
    public String llenarMascotaForm(int idMascota) {
        Gson gson = new Gson();
        DBConnection conn = new DBConnection();
        String sql = "SELECT nombre_mascota, edad, especie, raza, fundacion.nombre, estado, descripcion " +
                "FROM mascota INNER JOIN raza USING(id_raza) " +
                "INNER JOIN especie USING(id_especie) " +
                "INNER JOIN fundacion USING(id_fundacion) WHERE id_mascota = " + idMascota;

        try {
            Statement stm = conn.getConnection().createStatement();
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                String nombreMascota = rs.getString("nombre_mascota");
                float edad = rs.getFloat("edad");
                String descripcion = rs.getString("descripcion");
                boolean estado = rs.getBoolean("estado");
                String especie = rs.getString("especie");
                String raza = rs.getString("raza");
                String fundacion = rs.getString("fundacion.nombre");

                Mascota mascota = new Mascota(idMascota, nombreMascota, edad, descripcion, estado, especie, raza, fundacion);
                return gson.toJson(mascota);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());;
        } finally {
            conn.desconectar();
        }
        return "false";
    }

    @Override
    public String llenarMascotaModal(int idMascota) {
        Gson gson = new Gson();
        DBConnection conn = new DBConnection();
        String sql = "SELECT nombre_mascota " +
                "FROM mascota " +
                "WHERE id_mascota = " + idMascota;

        try {
            Statement stm = conn.getConnection().createStatement();
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                String nombreMascota = rs.getString("nombre_mascota");
                Mascota mascota = new Mascota(idMascota, nombreMascota);
                return gson.toJson(mascota);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());;
        } finally {
            conn.desconectar();
        }
        return "false";
    }
}
