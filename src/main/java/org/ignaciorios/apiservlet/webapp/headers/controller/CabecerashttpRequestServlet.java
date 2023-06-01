package org.ignaciorios.apiservlet.webapp.headers.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet("/cabeceras-request")
public class CabecerashttpRequestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        String metodoHttp = req.getMethod();
        String requestUri = req.getRequestURI();
        String requestURL = req.getRequestURL().toString();
        String contextPath = req.getContextPath();
        String servletPath = req.getServletPath();

        String ip = req.getLocalAddr();
        int port = req.getLocalPort();
        String schema = req.getScheme();
        String host = req.getHeader("host");
        String ipCliente = req.getRemoteAddr();

        try (PrintWriter out = resp.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("    <meta charset=\"UTF-8\">");
            out.println("    <title>Cabeceras HTTP Request</title>");
            out.println("    <style>");
            out.println("        body {");
            out.println("            font-family: Arial, sans-serif;");
            out.println("            margin: 20px;");
            out.println("        }");
            out.println("        h2 {");
            out.println("            color: #1c3b6b;");
            out.println("        }");
            out.println("        ul {");
            out.println("            list-style-type: none;");
            out.println("            padding: 0;");
            out.println("        }");
            out.println("        li {");
            out.println("            margin-bottom: 10px;");
            out.println("        }");
            out.println("        .separator {");
            out.println("            border-top: 1px solid #ccc;");
            out.println("            margin: 20px 0;");
            out.println("        }");
            out.println("    </style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h2>Cabeceras HTTP Request</h2>");
            out.println("<ul>");
            out.println("<li>Método HTTP: " + metodoHttp + "</li>");
            out.println("<li>Request URI: " + requestUri + "</li>");
            out.println("<li>Request URL: " + requestURL + "</li>");
            out.println("<li>Context Path: " + contextPath + "</li>");
            out.println("<li>Servlet Path: " + servletPath + "</li>");
            out.println("<li>IP: " + ip + "</li>");
            out.println("<li>Port: " + port + "</li>");
            out.println("<li>Schema: " + schema + "</li>");
            out.println("<li>Host: " + host + "</li>");
            out.println("<li>IP Cliente: " + ipCliente + "</li>");
            out.println("<li class=\"separator\"></li>");
            Enumeration<String> cabeceras = req.getHeaderNames();
            while (cabeceras.hasMoreElements()){
                String c2 = cabeceras.nextElement();
                out.println("<li>" + c2 + ": " + req.getHeader(c2) + "</li>");
            }
            out.println("</ul>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
