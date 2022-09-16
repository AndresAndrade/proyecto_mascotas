package com.proyecto_mascotas.beans;

public class Usuario extends Persona{
    private int idUsuario;
    private String username;
    private String password;
    private int idFundacion;


    public Usuario(int idUsuario, String username, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, String email, String telefono, String password, int idUbicacion, int idFundacion) {
        super(primerNombre, segundoNombre, primerApellido, segundoApellido, email, telefono, idUbicacion);
        this.idUsuario = idUsuario;
        this.username = username;
        this.password = password;
        this.idFundacion = idFundacion;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdFundacion() {
        return idFundacion;
    }

    public void setIdFundacion(int idFundacion) {
        this.idFundacion = idFundacion;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", idFundacion=" + idFundacion +
                "} " + super.toString();
    }
}