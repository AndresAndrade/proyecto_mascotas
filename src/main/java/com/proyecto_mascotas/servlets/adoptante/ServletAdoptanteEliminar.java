package com.proyecto_mascotas.servlets.adoptante;

import com.proyecto_mascotas.controller.AdoptanteController;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ServletAdoptanteEliminar", value = "/ServletAdoptanteEliminar")
public class ServletAdoptanteEliminar extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ServletAdoptanteEliminar() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AdoptanteController adoptante = new AdoptanteController();

        long cedula = Long.parseLong(request.getParameter("cedula"));

        String adoptanteStr = adoptante.eliminarAdoptante(cedula);

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(adoptanteStr);
        out.flush();
        out.close();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
