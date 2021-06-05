package ua.willkaxxx.demo.servlet_exhibition.controller.commands.auth;

import ua.willkaxxx.demo.servlet_exhibition.controller.commands.Command;
import ua.willkaxxx.demo.servlet_exhibition.model.entity.User;
import ua.willkaxxx.demo.servlet_exhibition.model.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class UserHome implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("userExhibitions", new UserService().getExhibitions(
                ((Optional<User>) request.getSession().getAttribute("user")).get()));
        request.getRequestDispatcher("/auth/userHome.jsp").forward(request, response);
    }
}
