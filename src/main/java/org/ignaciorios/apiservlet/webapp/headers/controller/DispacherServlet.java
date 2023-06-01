package org.ignaciorios.apiservlet.webapp.headers.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/dispatcher")
public class DispacherServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Se obtiene el objeto RequestDispatcher del contexto del servlet
        // El parámetro "/productos.html" indica la ruta del recurso al que se va a redirigir la solicitud
        // getRequestDispatcher() se utiliza para redirigir la solicitud a otro recurso o servlet en el servidor
        getServletContext().getRequestDispatcher("/productos.html").forward(req, resp);
    }
}
