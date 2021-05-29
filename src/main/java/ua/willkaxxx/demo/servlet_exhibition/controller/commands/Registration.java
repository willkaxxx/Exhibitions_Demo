package ua.willkaxxx.demo.servlet_exhibition.controller.commands;

import org.apache.log4j.Logger;
import ua.willkaxxx.demo.servlet_exhibition.model.entity.Role;
import ua.willkaxxx.demo.servlet_exhibition.model.entity.User;
import ua.willkaxxx.demo.servlet_exhibition.model.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class Registration implements Command {
    private final Logger log = Logger.getLogger(Registration.class);
    UserService userService = new UserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User user = new User();
        boolean dataValid = true;
        if (request.getParameter("email").length() < 2) {
            request.setAttribute("reg_email_error", "Invalid email, length must be more then 2");
            dataValid = false;
        }
        if (request.getParameter("pass").length() < 3) {
            request.setAttribute("reg_pass_error", "Invalid password, length must be more then 3");
            dataValid = false;
        }
        if (dataValid) {
            user.setEmail(request.getParameter("email"));
            user.setPassword(request.getParameter("pass"));
            user.setRole(Role.Authorized);
            try{
                userService.createUser(user);
            } catch (SQLException e) {
                if(e.getSQLState().equals("23000")) {
                    request.setAttribute("exist_error", "User with this email is already exists");
                    return "/user/registration.jsp";
                }
            }

            log.info("New user registered: " + user);
            return "redirect:/index.jsp";
        }
        log.info("User data incorrect");
        return "/user/registration.jsp";
    }
}