package com.proyecto_mascotas.servlets.fundacion;

import com.proyecto_mascotas.controller.FundacionController;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ServletFundacionModal", value = "/ServletFundacionModal")
public class ServletFundacionModal extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ServletFundacionModal() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FundacionController fundacion = new FundacionController();

        int idFundacion = Integer.parseInt(request.getParameter("idFundacion"));

        String fundacionStr = fundacion.llenarFundacionModal(idFundacion);

        PrintWriter out = response.getWriter();
        out.println(fundacionStr);
        out.flush();
        out.close();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
