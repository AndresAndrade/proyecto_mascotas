package com.proyecto_mascotas.servlets;

import com.proyecto_mascotas.controller.UsuarioController;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ServletUsuarioRegister", value = "/ServletUsuarioRegister")
public class ServletUsuarioRegister extends HttpServlet {
    public ServletUsuarioRegister() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UsuarioController usuario = new UsuarioController();

        int idUsuario = Integer.parseInt(request.getParameter("id_usuario"));
        String username = request.getParameter("username");
        String primerNombre = request.getParameter("primer_nombre");
        String segundoNombre = request.getParameter("segundo_nombre");
        String primerApellido = request.getParameter("primer_apellido");
        String segundoApellido = request.getParameter("segundo_apellido");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");
        String password = request.getParameter("password");
        int idFundacion = Integer.parseInt(request.getParameter("id_fundacion"));
        int idUbicacion = Integer.parseInt(request.getParameter("id_ubicacon"));

        String result = usuario.register(username, primerNombre, segundoNombre, primerApellido, segundoApellido, email, telefono, password, idUbicacion, idFundacion);

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(result);
        out.flush();
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
