package ua.willkaxxx.demo.servlet_exhibition.controller;

import ua.willkaxxx.demo.servlet_exhibition.controller.commands.Command;
import ua.willkaxxx.demo.servlet_exhibition.controller.commands.Registration;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet(name = "Servlet", value = "/*")
public class Servlet extends HttpServlet {
    //    private final Logger log = Logger.getLogger(Servlet.class);
    private Map<String, Command> commands = new HashMap<>();

    public void init() {
//        commands.put("logout", new LogOut());
//        commands.put("login", new Login());
        commands.put("registration", new Registration());
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String path = request.getRequestURI();
        response.sendRedirect(path);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getRequestURI();
        System.out.println(path);
        path = path.replaceAll(".*/exhibitions/", "");
        System.out.println(path);
        Command command = commands.getOrDefault(path,
                (r) -> "/index.jsp)");
        String page = command.execute(request);
        if (page.contains("redirect:")) {
            System.out.println("redirect");
            response.sendRedirect(page.replace("redirect:", "/"));
        } else {
            System.out.println("forwarded");
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}