package com.proyecto_mascotas.servlets.fundacion;

import com.proyecto_mascotas.controller.FundacionController;
import com.proyecto_mascotas.controller.MascotasController;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ServletFundacionRegister", value = "/ServletFundacionRegister")
public class ServletFundacionRegister extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public ServletFundacionRegister() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        FundacionController fundacion = new FundacionController();

        String nombre = request.getParameter("nombre");
        String telefono = request.getParameter("telefono");
        String email = request.getParameter("email");
        int idCiudad = Integer.parseInt(request.getParameter("idCiudad"));

        String result = fundacion.registrarFundacion(nombre, telefono, email, idCiudad);

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
