package com.proyecto_mascotas.servlets.ubicacion;

import com.proyecto_mascotas.controller.UbicacionController;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ServletCiudadListar", value = "/ServletCiudadListar")
public class ServletCiudadListar extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ServletCiudadListar() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UbicacionController ciudad = new UbicacionController();

        String departamento = request.getParameter("departamento");

        String ciudadStr = ciudad.listarCiudad(departamento);

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(ciudadStr);
        out.flush();
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
