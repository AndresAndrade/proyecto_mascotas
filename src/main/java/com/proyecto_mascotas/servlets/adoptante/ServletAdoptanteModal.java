package com.proyecto_mascotas.servlets.adoptante;

import com.proyecto_mascotas.controller.AdoptanteController;
import com.proyecto_mascotas.controller.MascotasController;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ServletAdoptanteModal", value = "/ServletAdoptanteModal")
public class ServletAdoptanteModal extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ServletAdoptanteModal() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AdoptanteController adoptante = new AdoptanteController();
        long cedula = Long.parseLong(request.getParameter("cedula"));

        String adoptanteStr = adoptante.llenarAdoptanteModal(cedula);

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
