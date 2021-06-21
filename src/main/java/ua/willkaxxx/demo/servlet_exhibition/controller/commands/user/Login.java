package ua.willkaxxx.demo.servlet_exhibition.controller.commands.user;

import org.apache.log4j.Logger;
import ua.willkaxxx.demo.servlet_exhibition.controller.commands.Command;
import ua.willkaxxx.demo.servlet_exhibition.model.services.AuthService;
import ua.willkaxxx.demo.servlet_exhibition.model.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Login implements Command {
    private final Logger log = Logger.getLogger(Login.class);
    AuthService authService = new AuthService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (authService.checkCredentials(request)) {
            if(authService.login(request)){
                response.sendRedirect("/exhibitions/index");
                return;
            }
            request.setAttribute("exist_error", true);
            request.getRequestDispatcher("/user/login.jsp").forward(request, response);
            return;
        }
        log.info("User data incorrect");
        request.getRequestDispatcher("/user/login.jsp").forward(request, response);
    }
}
