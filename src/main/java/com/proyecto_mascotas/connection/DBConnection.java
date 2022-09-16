package com.proyecto_mascotas.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    static String db = "bd_adopcion_mascotas";
    static String port = "3306";
    static String login = "root";
    static String password = "admin";

    Connection connection = null;

    public DBConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:" + this.port + "/" + this.db;
            connection = DriverManager.getConnection(url, this.login, this.password);
            if (connection == null) {
                System.out.println("Conexión fallida");
            } else {
                System.out.println("Conexión exitosa");
            }
        } catch (Exception ex) {
            System.err.println("ERROR " + ex.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void desconectar() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Error de conexión " + e.getMessage());
            }
        }
        connection = null;
    }
}
