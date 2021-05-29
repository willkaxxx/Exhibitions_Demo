package ua.willkaxxx.demo.servlet_exhibition.controller;

import org.apache.log4j.Logger;
import ua.willkaxxx.demo.servlet_exhibition.controller.commands.Command;
import ua.willkaxxx.demo.servlet_exhibition.controller.commands.Login;
import ua.willkaxxx.demo.servlet_exhibition.controller.commands.Registration;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet(name = "Servlet", value = "/*")
public class Servlet extends HttpServlet {
    private final Logger log = Logger.getLogger(Servlet.class);
    private Map<String, Command> commands = new HashMap<>();

    public void init() {
//        commands.put("logout", new LogOut());
        commands.put("login", new Login());
        commands.put("registration", new Registration());
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getRequestURI();
        path = path.replaceAll(".*/exhibitions/", "");
        log.info("Path = " + path);


        Optional<Command> command = Optional.ofNullable(commands.get(path));
        if (command.isPresent()){
            log.info("command -> " + path.replace("command:", ""));
            path = command.get().execute(request);
        }

        if (path.contains("redirect:")) {
            log.info("redirect -> " + path.replace("redirect:", ""));
            response.sendRedirect(path.replace("redirect:", ""));
        } else {
            log.info("forward -> " + path);
            request.getRequestDispatcher(path).forward(request, response);
        }
    }
}