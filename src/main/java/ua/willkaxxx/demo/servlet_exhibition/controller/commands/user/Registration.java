package ua.willkaxxx.demo.servlet_exhibition.controller.commands.user;

import org.apache.log4j.Logger;
import ua.willkaxxx.demo.servlet_exhibition.controller.Regexp;
import ua.willkaxxx.demo.servlet_exhibition.controller.commands.Command;
import ua.willkaxxx.demo.servlet_exhibition.model.entity.Role;
import ua.willkaxxx.demo.servlet_exhibition.model.entity.User;
import ua.willkaxxx.demo.servlet_exhibition.model.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class Registration implements Command {
    private final Logger log = Logger.getLogger(Registration.class);
    UserService userService = new UserService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User user = new User();
        boolean dataValid = true;
        if (!request.getParameter("email").matches(Regexp.EMAIL)) {
            request.setAttribute("reg_email_error", true);
            dataValid = false;
        }
        if (!request.getParameter("pass").matches(Regexp.PASSWORD)) {
            request.setAttribute("reg_pass_error", true);
            dataValid = false;
        }
        if (dataValid) {
            user.setEmail(request.getParameter("email"));
            user.setPassword(request.getParameter("pass"));
            user.setRole(Role.Authorized);
            try {
                userService.createUser(user);
            } catch (SQLException e) {
                if (e.getSQLState().equals("23000")) {
                    request.setAttribute("exist_error", true);
                    request.getRequestDispatcher("/user/registration.jsp").forward(request, response);
                    return;
                }
                log.error(e.getMessage());
            }
            log.info("New user registered: " + user);
            response.sendRedirect("/exhibitions/index");
            return;
        }
        log.info("User data incorrect");
        request.getRequestDispatcher("/user/registration.jsp").forward(request, response);
    }
}
