package com.proyecto_mascotas.servlets;

import com.proyecto_mascotas.controller.MascotasController;
import com.proyecto_mascotas.controller.UsuarioController;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ServletMascotaRegister", value = "/ServletMascotaRegister")
public class ServletMascotaRegister extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public ServletMascotaRegister() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        MascotasController mascota = new MascotasController();

        String nombreMascota = request.getParameter("nombreMascota");
        float edad = Float.parseFloat(request.getParameter("edad"));
        String descripcion = request.getParameter("descripcion");
        int idRaza = Integer.parseInt(request.getParameter("idRaza"));
        int idFundacion = Integer.parseInt(request.getParameter("idFundacion"));

        String result = mascota.registerMascota(nombreMascota, edad, descripcion, idRaza, idFundacion);

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(result);
        out.flush();
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
