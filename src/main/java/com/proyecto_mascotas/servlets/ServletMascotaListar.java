package com.proyecto_mascotas.servlets;

import com.proyecto_mascotas.controller.EspecieController;
import com.proyecto_mascotas.controller.MascotasController;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ServletMascotaListar", value = "/ServletMascotaListar")
public class ServletMascotaListar extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ServletMascotaListar() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MascotasController mascota = new MascotasController();

        String mascotaStr = mascota.listarMascotas();

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(mascotaStr);
        out.flush();
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
