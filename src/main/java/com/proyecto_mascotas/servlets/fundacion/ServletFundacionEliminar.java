package com.proyecto_mascotas.servlets.fundacion;

import com.proyecto_mascotas.controller.FundacionController;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ServletFundacionEliminar", value = "/ServletFundacionEliminar")
public class ServletFundacionEliminar extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ServletFundacionEliminar() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FundacionController fundacion = new FundacionController();

        int idFundacion = Integer.parseInt(request.getParameter("idFundacion"));

        String fundacionStr = fundacion.eliminarFundacion(idFundacion);

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(fundacionStr);
        out.flush();
        out.close();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
