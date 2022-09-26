package com.proyecto_mascotas.servlets.adoptante;

import com.proyecto_mascotas.controller.AdoptanteController;
import com.proyecto_mascotas.controller.UsuarioController;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ServletAdoptanteRegister", value = "/ServletAdoptanteRegister")
public class ServletAdoptanteRegister extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public ServletAdoptanteRegister() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AdoptanteController adoptante = new AdoptanteController();

        long cedula = Long.parseLong(request.getParameter("cedula"));
        String primerNombre = request.getParameter("primerNombre");
        String segundoNombre = request.getParameter("segundoNombre");
        String primerApellido = request.getParameter("primerApellido");
        String segundoApellido = request.getParameter("segundoApellido");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");
        String observacion = request.getParameter("observacion");
        int idCiudad = Integer.parseInt(request.getParameter("idCiudad"));

        String result = adoptante.register(cedula, primerNombre, segundoNombre, primerApellido, segundoApellido, email, telefono, observacion, idCiudad);

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
