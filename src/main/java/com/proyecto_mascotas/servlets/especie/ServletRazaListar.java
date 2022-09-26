package com.proyecto_mascotas.servlets.especie;

import com.proyecto_mascotas.controller.EspecieController;
import com.proyecto_mascotas.controller.UbicacionController;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ServletRazaListar", value = "/ServletRazaListar")
public class ServletRazaListar extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ServletRazaListar() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EspecieController raza = new EspecieController();

        String especie = request.getParameter("especie");

        String razaStr = raza.listarRaza(especie);

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(razaStr);
        out.flush();
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
