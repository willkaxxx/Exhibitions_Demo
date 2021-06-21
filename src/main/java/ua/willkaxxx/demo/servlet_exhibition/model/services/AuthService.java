package ua.willkaxxx.demo.servlet_exhibition.model.services;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.apache.log4j.Logger;
import ua.willkaxxx.demo.servlet_exhibition.controller.Regexp;
import ua.willkaxxx.demo.servlet_exhibition.model.entity.Role;
import ua.willkaxxx.demo.servlet_exhibition.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;

public class AuthService {
    private final Logger log = Logger.getLogger(AuthService.class);

    public boolean checkCredentials(HttpServletRequest request) {
        boolean dataValid = true;
        if (!request.getParameter("email").matches(Regexp.EMAIL)) {
            request.setAttribute("reg_email_error", true);
            dataValid = false;
        }
        if (!request.getParameter("pass").matches(Regexp.PASSWORD)) {
            request.setAttribute("reg_pass_error", true);
            dataValid = false;
        }
        return dataValid;
    }

    public void register(HttpServletRequest request) throws SQLException {
        UserService userService = new UserService();
        User user = new User.Builder().
                email(request.getParameter("email")).
                password(BCrypt.withDefaults().hashToString(12, request.getParameter("pass").toCharArray())).
                role(Role.Authorized).build();
        userService.createUser(user);
        log.info("New user registered: " + user);
    }

    public void logout(HttpServletRequest request) {
        HashSet<String> loggedUsers = (HashSet<String>) request.getServletContext().getAttribute("loggedUsers");
        HttpSession session = request.getSession();
        User curUser = ((Optional<User>) request.getSession().getAttribute("user")).get();
        if (loggedUsers.stream().anyMatch(curUser.getEmail()::equals)) {
            loggedUsers.remove(curUser.getEmail());
        }
        log.info("User " + curUser.getEmail() + " logged out");
        session.setAttribute("user", Optional.empty());
    }

    public boolean login(HttpServletRequest request) {
        UserService userService = new UserService();
        Optional<User> user = userService.findUser(request.getParameter("email"));
        HashSet<String> loggedUsers = (HashSet<String>) request.getServletContext().getAttribute("loggedUsers");
        if (user.isPresent()) {
            BCrypt.Result result = BCrypt.verifyer().verify(request.getParameter("pass").toCharArray(), user.get().getPassword());
            if (result.verified && loggedUsers.stream().noneMatch(user.get().getEmail()::equals)) {
                loggedUsers.add(user.get().getEmail());
                request.getSession().setAttribute("user", user);
                log.info("User : " + user.get().getEmail() + " logged in");
                return true;
            }
        }
        return false;
    }
}
