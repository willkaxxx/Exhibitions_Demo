package ua.willkaxxx.demo.servlet_exhibition.controller.commands;

import org.apache.log4j.Logger;
import ua.willkaxxx.demo.servlet_exhibition.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Logout implements Command{
    private final Logger log = Logger.getLogger(Logout.class);
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("user", null);
        log.info("User logged out");
        return "redirect:/index.jsp";
    }
}
