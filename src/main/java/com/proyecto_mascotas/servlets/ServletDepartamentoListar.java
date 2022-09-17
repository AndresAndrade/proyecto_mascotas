package com.proyecto_mascotas.servlets;

import com.proyecto_mascotas.controller.UbicacionController;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ServletDepartamentoListar", value = "/ServletDepartamentoListar")
public class ServletDepartamentoListar extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ServletDepartamentoListar() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UbicacionController departamento = new UbicacionController();

        String departamentoStr = departamento.listarDepartamento();

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(departamentoStr);
        out.flush();
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
