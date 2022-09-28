package com.proyecto_mascotas.servlets.mascota;

import com.proyecto_mascotas.controller.MascotasController;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ServletMascotaLlenarForm", value = "/ServletMascotaLlenarForm")
public class ServletMascotaLlenarForm extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public ServletMascotaLlenarForm() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MascotasController mascota = new MascotasController();
        int idMascota = Integer.parseInt(request.getParameter("idMascota"));
        String mascotaStr =  mascota.llenarMascotaForm(idMascota);

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
