package ua.willkaxxx.demo.servlet_exhibition.controller;

import org.apache.log4j.Logger;
import ua.willkaxxx.demo.servlet_exhibition.controller.commands.*;
import ua.willkaxxx.demo.servlet_exhibition.controller.commands.admin.exhibitions.*;
import ua.willkaxxx.demo.servlet_exhibition.controller.commands.admin.halls.ManageHalls;
import ua.willkaxxx.demo.servlet_exhibition.controller.commands.admin.halls.SaveHall;
import ua.willkaxxx.demo.servlet_exhibition.controller.commands.admin.halls.ShowEditHall;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Servlet", value = "/exhibitions/*")
public class Servlet extends HttpServlet {
    private final Logger log = Logger.getLogger(Servlet.class);
    private final Map<String, Command> commands = new HashMap<>();

    public void init() {
        commands.put("user/logout", new Logout());
        commands.put("login", new Login());
        commands.put("registration", new Registration());

        commands.put("admin/manageHalls", new ManageHalls());
        commands.put("admin/editHall", new ShowEditHall());
        commands.put("admin/saveHall", new SaveHall());
        commands.put("admin/manageExhibitions", new ManageExhibitions());
        commands.put("admin/editExhibition", new ShowEditExhibition());
        commands.put("admin/saveExhibition", new SaveExhibition());
        commands.put("admin/deleteHallFromExhibition", new DeleteHallFromExhibition());
        commands.put("admin/addHallToExhibition", new AddHallToExhibition());
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) {

        String path = request.getRequestURI();
        log.info("Raw path:" + path);
        path = path.replaceAll(".*/exhibitions/", "");

        if(!path.contains("redirect:") && !path.contains("forward:")){
            Command command = commands.getOrDefault(path,
                    (r) -> "redirect:/index.jsp");
            log.info(command.getClass());
            path = command.execute(request);
        }

        if (path.contains("redirect:"))
            sendRedirect(response, path);
        if(path.contains("forward:"))
            forward(response, request, path);
    }

    private void sendRedirect(HttpServletResponse response, String path) {
        try {
            path = path.replace("redirect:", "");
            log.trace("redirect -> " + path);
            response.sendRedirect(path);
        } catch (IOException e) {
            log.trace("Error while redirecting");
            throw new RuntimeException(e);
        }
    }

    private void forward(HttpServletResponse response, HttpServletRequest request, String path) {
        try {
            path = path.replace("forward:", "");
            log.info("forward -> " + path);
            request.getRequestDispatcher(path).forward(request, response);
        } catch (IOException | ServletException e) {
            log.error("Error while forwarding");
            throw new RuntimeException(e);
        }
    }
}