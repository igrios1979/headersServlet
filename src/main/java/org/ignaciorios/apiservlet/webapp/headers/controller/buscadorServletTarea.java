package org.ignaciorios.apiservlet.webapp.headers.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.ignaciorios.apiservlet.webapp.headers.models.Producto;
import org.ignaciorios.apiservlet.webapp.headers.service.ProductoService;
import org.ignaciorios.apiservlet.webapp.headers.service.ProductoServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet("/tareabuscador")
public class buscadorServletTarea extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductoService service = new ProductoServiceImpl();
        String nombre = req.getParameter("producto");

        Optional<Producto> hallado =  service.buscarProducto(nombre);
        if(hallado.isPresent()) {

            resp.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = resp.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("    <meta charset=\"UTF-8\">");
                out.println("    <title>OK perfecto el Producto existe ¡¡¡</title>");
                out.println("    <link rel=\"stylesheet\" href=\"styles.css\">");
                out.println("</head>");
                out.println("<body>");
                out.println("<h2>OK perfecto el Producto existe ¡¡¡</h2>");
                out.println("<h3>"+ hallado.get().getNombre()+"--->Precio $ = "+hallado.get().getPecio()+"</h3>");
                out.println("</body>");
                out.println("</html>");
            }


        }else resp.sendError(404);

    }

    }



