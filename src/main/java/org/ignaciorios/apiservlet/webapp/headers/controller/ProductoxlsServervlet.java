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
import java.util.List;

@WebServlet({"/producto.xls","/productos.html"})
public class ProductoxlsServervlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Crear instancia del servicio de productos
        ProductoService ps = new ProductoServiceImpl();

        // Obtener lista de productos
        List<Producto> productos = ps.listar();

        // Configurar el tipo de contenido de la respuesta HTTP
        resp.setContentType("text/html;charset=UTF-8");

        // Obtener la ruta del servlet
        String servletPath = req.getServletPath();

        // Verificar si la solicitud es para exportar a XLS
        boolean esXls = servletPath.endsWith(".xls");

        if (esXls) {
            // Configurar el tipo de contenido para exportar a XLS
            resp.setContentType("application/vnd.ms-excel");

            // Configurar la cabecera para descargar el archivo XLS
            resp.setHeader("Content-Disposition","attachment;filename=productos.xls");
        }

        try (PrintWriter out = resp.getWriter()) {
            if (!esXls) {
                // Respuesta para la solicitud HTML
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("    <meta charset=\"UTF-8\">");
                out.println("    <title>Listado </title>");
                out.println("    <link rel=\"stylesheet\" href=\"styles.css\">");
                out.println("</head>");
                out.println("<body>");
                out.println("<h2>Listado</h2>");
                out.println("<p><a href=\"" + req.getContextPath() + "/producto.xls" + "\"> exportar a XLS </a>");
            }

            // Generar la tabla de productos
            out.println("<table>");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<th>id</th>");
            out.println("<th>Nombre</th>");
            out.println("<th>tipo</th>");
            out.println("<th>precio</th>");
            out.println("</tr>");
            out.println("</thead>");

            // Iterar sobre la lista de productos y agregar cada uno como una fila en la tabla
            out.println("<tr>");
            productos.forEach(p-> {
                out.println("<td>" + p.getId()+ "</td>");
                out.println("<td>" + p.getNombre()+ "</td>");
                out.println("<td>" + p.getTipo() + "</td>");
                out.println("<td>" + p.getPecio() + "</td>");
                out.println("</tr>");
            });

            out.println("</table>");

            if (!esXls) {
                out.println("</body>");
                out.println("</html>");

            }
        }
    }
}
