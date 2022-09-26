package com.proyecto_mascotas.servlets.usuario;

import com.proyecto_mascotas.controller.UsuarioController;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ServletUsuarioModal", value = "/ServletUsuarioModal")
public class ServletUsuarioModal extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ServletUsuarioModal() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UsuarioController usuario = new UsuarioController();

        int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));

        String usuarioStr = usuario.llenarUsuarioModal(idUsuario);

        PrintWriter out = response.getWriter();
        out.println(usuarioStr);
        out.flush();
        out.close();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
