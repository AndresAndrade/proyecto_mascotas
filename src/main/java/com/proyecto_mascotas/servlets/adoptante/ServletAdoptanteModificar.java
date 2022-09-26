package com.proyecto_mascotas.servlets.adoptante;

import com.proyecto_mascotas.controller.AdoptanteController;
import com.proyecto_mascotas.controller.UsuarioController;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ServletAdoptanteModificar", value = "/ServletAdoptanteModificar")
public class ServletAdoptanteModificar extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ServletAdoptanteModificar() {
        super();
    }

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

        String adoptanteStr = adoptante.editarAdoptante(cedula, primerNombre, segundoNombre, primerApellido, segundoApellido, email, telefono, observacion);

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(adoptanteStr);
        out.flush();
        out.close();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
