package ua.willkaxxx.demo.servlet_exhibition.controller.commands.user;

import org.apache.log4j.Logger;
import ua.willkaxxx.demo.servlet_exhibition.controller.commands.Command;
import ua.willkaxxx.demo.servlet_exhibition.controller.commands.InputDataValidator;
import ua.willkaxxx.demo.servlet_exhibition.model.services.AuthService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 *  This class implements command pattern and logs in a user
 */
public class Login implements Command {
    private final Logger log = Logger.getLogger(Login.class);
    AuthService authService;

    public Login(AuthService authService){
        this.authService = authService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (!InputDataValidator.validateCredentials(request)) {
            log.info("User data incorrect");
            request.getRequestDispatcher("/user/login.jsp").forward(request, response);
        }
        if (!authService.login(request)) {
            request.getRequestDispatcher("/user/login.jsp").forward(request, response);
            return;
        }
        response.sendRedirect("/exhibitions/index");
    }
}
