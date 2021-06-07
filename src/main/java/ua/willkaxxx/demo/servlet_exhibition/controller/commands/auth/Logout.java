package ua.willkaxxx.demo.servlet_exhibition.controller.commands.auth;

import org.apache.log4j.Logger;
import ua.willkaxxx.demo.servlet_exhibition.controller.commands.Command;
import ua.willkaxxx.demo.servlet_exhibition.model.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

public class Logout implements Command {
    private final Logger log = Logger.getLogger(Logout.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        log.info("User " + ((Optional<User>) request.getSession().getAttribute("user")).get().getEmail() +" logged out");
        session.setAttribute("user", null);
        response.sendRedirect("/exhibitions/index");
    }
}
