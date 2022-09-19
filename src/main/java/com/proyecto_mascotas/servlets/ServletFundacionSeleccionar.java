package com.proyecto_mascotas.servlets;

import com.proyecto_mascotas.controller.FundacionController;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ServletFundacionSeleccionar", value = "/ServletFundacionSeleccionar")
public class ServletFundacionSeleccionar extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ServletFundacionSeleccionar() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        FundacionController fundacion = new FundacionController();

        String fundacionStr = fundacion.seleccionarFundacion();

        response.setContentType("text/html;charset=UTF-8");
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
