package ua.willkaxxx.demo.servlet_exhibition.controller.commands;

import org.apache.log4j.Logger;
import ua.willkaxxx.demo.servlet_exhibition.model.entity.User;
import ua.willkaxxx.demo.servlet_exhibition.model.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class Login implements Command{
    private final Logger log = Logger.getLogger(Login.class);
    UserService userService = new UserService();
    @Override
    public String execute(HttpServletRequest request) {
        boolean dataValid = true;
        if (request.getParameter("email").length() < 2) {
            request.setAttribute("email_error", "Invalid email, length must be more then 2");
            dataValid = false;
        }
        if (request.getParameter("pass").length() < 3) {
            request.setAttribute("pass_error", "Invalid password, length must be more then 3");
            dataValid = false;
        }
        if (dataValid) {
            Optional<User> user = userService.findUser(request.getParameter("email"));
            if(user.isPresent())
                if(user.get().getPassword()
                        .equals(request.getParameter("pass"))){//Todo add password encryption
                    HttpSession httpSession = request.getSession();
                    httpSession.setAttribute("user", user);
                    log.info("User : " + user.get() + " logged in");
                    return "redirect:/index.jsp";
                }
            request.setAttribute("exist_error", "Email or password is incorrect");
            return "/user/login.jsp";
        }
        log.info("User data incorrect");
        return "/user/login.jsp";
    }
}
