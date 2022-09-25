package com.proyecto_mascotas.servlets;

import com.proyecto_mascotas.controller.MascotasController;
import com.proyecto_mascotas.controller.UsuarioController;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ServletUsuarioLlenarForm", value = "/ServletUsuarioLlenarForm")
public class ServletUsuarioLlenarForm extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ServletUsuarioLlenarForm() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UsuarioController usuario = new UsuarioController();
        int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
        String usuarioStr =  usuario.llenarUsuarioFrom(idUsuario);

        PrintWriter out = response.getWriter();
        out.println(usuarioStr);
        out.flush();
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
