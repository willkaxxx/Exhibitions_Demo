package ua.willkaxxx.demo.servlet_exhibition.controller.commands.user;

import org.apache.log4j.Logger;
import ua.willkaxxx.demo.servlet_exhibition.model.services.AuthService;
import ua.willkaxxx.demo.servlet_exhibition.controller.commands.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class Registration implements Command {
    private final Logger log = Logger.getLogger(Registration.class);
    AuthService authService = new AuthService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (authService.checkCredentials(request)) {
            try {
                authService.register(request);
                authService.login(request);
            } catch (SQLException e) {
                if (e.getSQLState().equals("23000")) {
                    request.setAttribute("exist_error", true);
                    request.getRequestDispatcher("/user/registration.jsp").forward(request, response);
                    return;
                }
                log.error(e.getMessage());
            }
            response.sendRedirect("/exhibitions/index");
            return;
        }
        log.info("User data incorrect");
        request.getRequestDispatcher("/user/registration.jsp").forward(request, response);
    }
}
