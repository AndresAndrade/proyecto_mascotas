package com.proyecto_mascotas.beans;

public class Usuario extends Persona{
    private int idUsuario;
    private String username;
    private String password;
    private int idFundacion;
    private String fundacion;

    public Usuario(int idUsuario, String username, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, String email, String telefono, String password, int idCiudad, int idFundacion) {
        super(primerNombre, segundoNombre, primerApellido, segundoApellido, email, telefono, idCiudad);
        this.idUsuario = idUsuario;
        this.username = username;
        this.password = password;
        this.idFundacion = idFundacion;
    }

    //Para insertar el usuario en la DB
    public Usuario(String username, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, String email, String telefono, String password, int idUbicacion, int idFundacion) {
        super(primerNombre, segundoNombre, primerApellido, segundoApellido, email, telefono, idUbicacion);
        this.username = username;
        this.password = password;
        this.idFundacion = idFundacion;
    }

    //Para listar en el HTML
    public Usuario(int idUsuario, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, String email, String telefono, int idCiudad, String fundacion) {
        super(primerNombre, segundoNombre, primerApellido, segundoApellido, email, telefono, idCiudad);
        this.idUsuario = idUsuario;
        this.fundacion = fundacion;
    }

    /*Para llenar el formulario de edición*/
    public Usuario(String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, String email, String telefono, String ciudad, String departamento, int idUsuario, String username, String password, String fundacion) {
        super(primerNombre, segundoNombre, primerApellido, segundoApellido, email, telefono, ciudad, departamento);
        this.idUsuario = idUsuario;
        this.username = username;
        this.password = password;
        this.fundacion = fundacion;
    }

    public String getFundacion() {
        return fundacion;
    }

    public void setFundacion(String fundacion) {
        this.fundacion = fundacion;
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
                ", fundacion='" + fundacion + '\'' +
                "} " + super.toString();
    }
}