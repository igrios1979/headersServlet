package org.ignaciorios.apiservlet.webapp.headers.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.ignaciorios.apiservlet.webapp.headers.models.Producto;
import org.ignaciorios.apiservlet.webapp.headers.service.ProductoService;
import org.ignaciorios.apiservlet.webapp.headers.service.ProductoServiceImpl;

import java.io.IOException;
import java.util.List;
import java.io.PrintWriter;

@WebServlet("/productos-json")
public class ProductoJsonServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Se crea una instancia del servicio de productos
        ProductoService ps = new ProductoServiceImpl();

        // Se obtiene la lista de productos
        List<Producto> lp = ps.listar();

        // Se crea una instancia del ObjectMapper para convertir la lista de productos a JSON
        ObjectMapper mapper = new ObjectMapper();

        // Se convierte la lista de productos a JSON
        String json = mapper.writeValueAsString(lp);

        // Se establece el tipo de contenido de la respuesta como JSON
        resp.setContentType("application/json");

        // Se escribe la respuesta JSON en el cuerpo de la respuesta
        resp.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Se obtiene el flujo de entrada de la solicitud
        ServletInputStream jsonStream = req.getInputStream();

        // Se crea una instancia del ObjectMapper para convertir el JSON a un objeto Producto
        ObjectMapper mapper = new ObjectMapper();

        // Se convierte el JSON en un objeto Producto
        Producto producto =  mapper.readValue(jsonStream, Producto.class);

        // Se establece el tipo de contenido de la respuesta como HTML con codificación UTF-8
        resp.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = resp.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("    <meta charset=\"UTF-8\">");
            out.println("    <title>JSON de entrada </title>");
            out.println("    <link rel=\"stylesheet\" href=\"styles.css\">");
            out.println("</head>");
            out.println("<body>");
            out.println("<h2>Detalle de Producto desde Json  </h2>");

            // Se muestra la información del producto en una lista no ordenada
            out.println("<ul>");
            out.println("<li>Id: "+     producto.getId()+ "</li>" );
            out.println("<li>nombre: "+ producto.getNombre()+ "</li>" );
            out.println("<li>tipo: "+   producto.getTipo() + "</li>" );
            out.println("<li>precio: "+ producto.getPecio()+ "</li>" );
            out.println("</ul>");

            out.println("</body>");
            out.println("</html>");
        }
    }
}
