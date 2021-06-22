package ua.willkaxxx.demo.servlet_exhibition.controller.commands.user;

import org.apache.log4j.Logger;
import ua.willkaxxx.demo.servlet_exhibition.controller.Regexp;
import ua.willkaxxx.demo.servlet_exhibition.controller.commands.Command;
import ua.willkaxxx.demo.servlet_exhibition.model.services.AuthService;
import ua.willkaxxx.demo.servlet_exhibition.model.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Login implements Command {
    private final Logger log = Logger.getLogger(Login.class);
    AuthService authService;

    public Login(AuthService authService){
        this.authService = authService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        boolean dataValid = true;
        if (request.getParameter("email") != null && !request.getParameter("email").matches(Regexp.EMAIL)) {
            request.setAttribute("reg_email_error", true);
            dataValid = false;
        }
        if (request.getParameter("pass") != null &&  !request.getParameter("pass").matches(Regexp.PASSWORD)) {
            request.setAttribute("reg_pass_error", true);
            dataValid = false;
        }
        if (dataValid) {
            if(authService.login(request)){
                response.sendRedirect("/exhibitions/index");
                return;
            }
            request.getRequestDispatcher("/user/login.jsp").forward(request, response);
            return;
        }
        log.info("User data incorrect");
        request.getRequestDispatcher("/user/login.jsp").forward(request, response);
    }
}
