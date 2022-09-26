package com.proyecto_mascotas.servlets.fundacion;

import com.proyecto_mascotas.controller.FundacionController;
import com.proyecto_mascotas.controller.MascotasController;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ServletFundacionLlenarForm", value = "/ServletFundacionLlenarForm")
public class ServletFundacionLlenarForm extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ServletFundacionLlenarForm() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FundacionController fundacion = new FundacionController();
        int idFundacion = Integer.parseInt(request.getParameter("idFundacion"));
        String fundacionStr =  fundacion.llenarFundacionForm(idFundacion);

        PrintWriter out = response.getWriter();
        out.println(fundacionStr);
        out.flush();
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
