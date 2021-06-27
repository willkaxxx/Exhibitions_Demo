package ua.willkaxxx.demo.servlet_exhibition.controller.commands.auth;

import ua.willkaxxx.demo.servlet_exhibition.controller.commands.Command;
import ua.willkaxxx.demo.servlet_exhibition.model.entity.Exhibition;
import ua.willkaxxx.demo.servlet_exhibition.model.entity.User;
import ua.willkaxxx.demo.servlet_exhibition.model.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
/**
 *  This class implements command pattern and enrolls user to exhibition
 */
public class EnrollToExhibition implements Command {
    UserService userService;
    public EnrollToExhibition(UserService userService){
        this.userService = userService;
    }
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        userService.enroll(
                new Exhibition.Builder().id(Integer.parseInt(request.getParameter("exhibitionId"))).build(),
                ((Optional<User>) request.getSession().getAttribute("user")).get());
        response.sendRedirect("/exhibitions/auth/userHome");
    }
}
