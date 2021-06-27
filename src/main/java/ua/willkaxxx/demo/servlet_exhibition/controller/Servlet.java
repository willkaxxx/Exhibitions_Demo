package ua.willkaxxx.demo.servlet_exhibition.controller;

import org.apache.log4j.Logger;
import ua.willkaxxx.demo.servlet_exhibition.controller.commands.*;
import ua.willkaxxx.demo.servlet_exhibition.controller.commands.admin.exhibitions.*;
import ua.willkaxxx.demo.servlet_exhibition.controller.commands.admin.halls.ManageHalls;
import ua.willkaxxx.demo.servlet_exhibition.controller.commands.admin.halls.SaveHall;
import ua.willkaxxx.demo.servlet_exhibition.controller.commands.admin.halls.ShowEditHall;
import ua.willkaxxx.demo.servlet_exhibition.controller.commands.auth.EnrollToExhibition;
import ua.willkaxxx.demo.servlet_exhibition.controller.commands.auth.UserHome;
import ua.willkaxxx.demo.servlet_exhibition.controller.commands.user.Login;
import ua.willkaxxx.demo.servlet_exhibition.controller.commands.auth.Logout;
import ua.willkaxxx.demo.servlet_exhibition.controller.commands.user.Registration;
import ua.willkaxxx.demo.servlet_exhibition.controller.commands.user.ShowHome;
import ua.willkaxxx.demo.servlet_exhibition.model.services.AuthService;
import ua.willkaxxx.demo.servlet_exhibition.model.services.ExhibitionService;
import ua.willkaxxx.demo.servlet_exhibition.model.services.HallService;
import ua.willkaxxx.demo.servlet_exhibition.model.services.UserService;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *  This class is Servlet that handles all input requests
 */
@WebServlet(name = "Servlet", value = "/exhibitions/*")
public class Servlet extends HttpServlet {
    private final Logger log = Logger.getLogger(Servlet.class);
    private final Map<String, Command> commands = new HashMap<>();

    private final AuthService authService = new AuthService();
    private final HallService hallService = new HallService();
    private final UserService userService = new UserService();
    private final ExhibitionService exhibitionService = new ExhibitionService();

    public void init() {
        getServletContext().setAttribute("loggedUsers", new HashSet<String>());

        commands.put("login", new Login(authService));
        commands.put("registration", new Registration(authService));
        commands.put("index", new ShowHome(exhibitionService));

        commands.put("admin/manageHalls", new ManageHalls(hallService));
        commands.put("admin/editHall", new ShowEditHall(hallService));
        commands.put("admin/saveHall", new SaveHall(hallService));
        commands.put("admin/manageExhibitions", new ManageExhibitions(exhibitionService));
        commands.put("admin/editExhibition", new ShowEditExhibition(exhibitionService));
        commands.put("admin/saveExhibition", new SaveExhibition(exhibitionService));
        commands.put("admin/deleteHallFromExhibition", new DeleteHallFromExhibition(exhibitionService));
        commands.put("admin/addHallToExhibition", new AddHallToExhibition(exhibitionService));
        commands.put("admin/deleteExhibition", new DeleteExhibition(exhibitionService));

        commands.put("auth/enroll", new EnrollToExhibition(userService));
        commands.put("auth/userHome", new UserHome(userService));
        commands.put("auth/logout", new Logout(authService));
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String path = request.getRequestURI();
        log.trace("Processing: " + path);
        path = path.replaceAll(".*/exhibitions/", "");

        if (!path.contains("redirect:") && !path.contains("forward:")) {
            commands.getOrDefault(path,
                    commands.get("index")).execute(request,response);
            return;
        }

        if (path.contains("redirect:"))
            sendRedirect(response, path);
        if (path.contains("forward:"))
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