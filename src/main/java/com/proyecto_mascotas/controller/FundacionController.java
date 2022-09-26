package com.proyecto_mascotas.controller;

import com.google.gson.Gson;
import com.proyecto_mascotas.beans.Adoptante;
import com.proyecto_mascotas.beans.Fundacion;
import com.proyecto_mascotas.beans.Mascota;
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

    @Override
    public String editarFundacion(int idFundacion, String nombreFundacion, String telefono, String email) {
        DBConnection con = new DBConnection();

        String sql = "UPDATE fundacion SET nombre = '" + nombreFundacion + "', telefono = '" + telefono + "', email = '" + email + "' WHERE id_fundacion = " + idFundacion;

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
    public String eliminarFundacion(int idFundacion) {
        DBConnection con = new DBConnection();

        String sql = "DELETE FROM fundacion WHERE id_fundacion = " + idFundacion;

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
    public String llenarFundacionForm(int idFundacion) {
        Gson gson = new Gson();
        DBConnection conn = new DBConnection();
        String sql = "SELECT nombre, telefono, email, ciudad, departamento " +
                "FROM fundacion " +
                "INNER JOIN ciudad USING(id_ciudad) " +
                "INNER JOIN departamento USING(id_departamento) " +
                "WHERE id_fundacion = " + idFundacion;

        try {
            Statement stm = conn.getConnection().createStatement();
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                String nombreFundacion = rs.getString("nombre");
                String telefono = rs.getString("telefono");
                String email = rs.getString("email");
                String ciudad = rs.getString("ciudad");
                String departamento = rs.getString("departamento");

                Fundacion fundacion = new Fundacion(idFundacion, nombreFundacion, telefono, email, ciudad, departamento);
                return gson.toJson(fundacion);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());;
        } finally {
            conn.desconectar();
        }
        return "false";
    }

    @Override
    public String llenarFundacionModal(int idFundacion) {
        Gson gson = new Gson();
        DBConnection conn = new DBConnection();
        String sql = "SELECT nombre " +
                "FROM fundacion " +
                "WHERE id_fundacion = " + idFundacion;

        try {
            Statement stm = conn.getConnection().createStatement();
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                String nombreFundacion = rs.getString("nombre");
                Fundacion fundacion = new Fundacion(idFundacion, nombreFundacion);
                return gson.toJson(fundacion);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());;
        } finally {
            conn.desconectar();
        }
        return "false";
    }
}


