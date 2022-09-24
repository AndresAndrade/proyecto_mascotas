package com.proyecto_mascotas.servlets;

import com.proyecto_mascotas.controller.MascotasController;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ServletMascotaModificar", value = "/ServletMascotaModificar")
public class ServletMascotaModificar extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ServletMascotaModificar() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MascotasController mascota = new MascotasController();

        int idMascota = Integer.parseInt(request.getParameter("idMascota"));
        String nombreMascota = request.getParameter("nombreMascota");
        float edad = Float.parseFloat(request.getParameter("edad"));
        String descripcion = request.getParameter("descripcion");
        boolean estado = Boolean.parseBoolean(request.getParameter("estado"));

        String mascotaStr = mascota.editarMascota(idMascota, nombreMascota, edad, descripcion, estado);

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(mascotaStr);
        out.flush();
        out.close();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
