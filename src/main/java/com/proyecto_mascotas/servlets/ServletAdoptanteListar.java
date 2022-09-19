package com.proyecto_mascotas.servlets;

import com.proyecto_mascotas.controller.AdoptanteController;
import com.proyecto_mascotas.controller.UsuarioController;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ServletAdoptanteListar", value = "/ServletAdoptanteListar")
public class ServletAdoptanteListar extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ServletAdoptanteListar() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        AdoptanteController adoptante = new AdoptanteController();

        String adoptanteStr = adoptante.listarAdoptantes();

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(adoptanteStr);
        out.flush();
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
