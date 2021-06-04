package ua.willkaxxx.demo.servlet_exhibition.controller.commands.auth;

import org.apache.log4j.Logger;
import ua.willkaxxx.demo.servlet_exhibition.controller.commands.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Logout implements Command {
    private final Logger log = Logger.getLogger(Logout.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        session.setAttribute("user", null);
        log.info("User logged out");
        response.sendRedirect("/exhibitions/index");
    }
}
