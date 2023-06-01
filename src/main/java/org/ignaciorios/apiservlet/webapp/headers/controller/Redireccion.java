package org.ignaciorios.apiservlet.webapp.headers.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet("/redireccion")
public class Redireccion extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

       // resp.setHeader("Location",req.getContextPath() + "/productos.html");
        //resp.setStatus(HttpServletResponse.SC_FOUND);
       resp.sendRedirect(req.getContextPath() + "/productos.html");

    }



}
