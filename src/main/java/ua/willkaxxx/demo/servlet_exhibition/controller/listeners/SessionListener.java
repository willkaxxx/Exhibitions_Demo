package ua.willkaxxx.demo.servlet_exhibition.controller.listeners;

import org.apache.log4j.Logger;
import ua.willkaxxx.demo.servlet_exhibition.model.entity.User;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashSet;
import java.util.Optional;

@WebListener
public class SessionListener implements HttpSessionListener {
    private final Logger log = Logger.getLogger(SessionListener.class);

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        httpSessionEvent.getSession().setAttribute("user", Optional.empty());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        Optional<User> curUser = ((Optional<User>) session.getAttribute("user"));
        if (curUser.isPresent()) {
            HashSet<String> loggedUsers = (HashSet<String>) session.getServletContext().getAttribute("loggedUsers");
            String userEmail = curUser.get().getEmail();
            if (loggedUsers.remove(userEmail))
                log.info("User " + userEmail + " logged out : Session timed out");
        }
    }

}
