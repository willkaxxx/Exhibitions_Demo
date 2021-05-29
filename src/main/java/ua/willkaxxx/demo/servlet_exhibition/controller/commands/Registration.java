package ua.willkaxxx.demo.servlet_exhibition.controller.commands;

import org.apache.log4j.Logger;
import ua.willkaxxx.demo.servlet_exhibition.model.entity.Role;
import ua.willkaxxx.demo.servlet_exhibition.model.entity.User;

import javax.servlet.http.HttpServletRequest;

public class Registration implements Command{
//    private final Logger log = Logger.getLogger(Registration.class);

    @Override
    public String execute(HttpServletRequest request) {
        User user = new User();
        if(request.getParameter("email").length() < 2 || request.getParameter("pass").length() < 3){
            request.setAttribute("error", "Invalid");
            return "/user/registration.jsp";
        }
        user.setEmail(request.getParameter("email"));
        user.setPassword(request.getParameter("pass"));
        user.setRole(Role.Authorized);
        System.out.println(user);
//        log.info(user);
        return "redirect:index.jsp";
    }
}
