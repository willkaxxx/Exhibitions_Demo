package ua.willkaxxx.demo.servlet_exhibition.controller.commands.auth;

import ua.willkaxxx.demo.servlet_exhibition.controller.commands.Command;
import ua.willkaxxx.demo.servlet_exhibition.model.services.AuthService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 *  This class implements command pattern and logs out a logged user
 */
public class Logout implements Command {
    AuthService authService;

    public Logout(AuthService authService){
        this.authService = authService;
    }
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        authService.logout(request);
        response.sendRedirect("/exhibitions/index");
    }
}
