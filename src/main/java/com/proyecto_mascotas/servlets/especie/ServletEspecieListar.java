package com.proyecto_mascotas.servlets.especie;

import com.proyecto_mascotas.controller.EspecieController;
import com.proyecto_mascotas.controller.UbicacionController;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ServletEspecieListar", value = "/ServletEspecieListar")
public class ServletEspecieListar extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ServletEspecieListar() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EspecieController especie = new EspecieController();

        String especieStr = especie.listarEspecie();

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(especieStr);
        out.flush();
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
