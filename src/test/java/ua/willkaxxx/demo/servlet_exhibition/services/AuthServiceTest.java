package ua.willkaxxx.demo.servlet_exhibition.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ua.willkaxxx.demo.servlet_exhibition.model.dao.UserDao;
import ua.willkaxxx.demo.servlet_exhibition.model.dao.impl.JDBCDaoFactory;
import ua.willkaxxx.demo.servlet_exhibition.model.entity.User;
import ua.willkaxxx.demo.servlet_exhibition.model.services.AuthService;
import ua.willkaxxx.demo.servlet_exhibition.model.services.UserService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.*;

public class AuthServiceTest {
    final String INVALID_USER_EMAIL = "nUser@mail";
    final String VALID_USER_EMAIL = "admin@admin";
    final String USER_PASS = "password";
    UserService userService = new UserService();
    AuthService authService = new AuthService();

    @Test
    public void testRegistration_valid() {
        Optional<User> user = Optional.empty();
        try {
            HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
            Mockito.when(request.getParameter("email")).thenReturn(INVALID_USER_EMAIL);
            Mockito.when(request.getParameter("pass")).thenReturn(USER_PASS);
            authService.register(request);
            user = userService.findUser(INVALID_USER_EMAIL);
            Assertions.assertTrue(user.isPresent());
        } catch (SQLException e) {
            Assertions.fail("Sql exception while inserting valid user");
        } finally {
            UserDao dao = JDBCDaoFactory.getInstance().createUserDao();
            user.ifPresent(value -> dao.delete(value.getId()));
        }
    }

    @Test
    public void testLogin_valid() {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        ServletContext servletContext = Mockito.mock(ServletContext.class);
        HttpSession httpSession = Mockito.mock(HttpSession.class);

        Mockito.when(servletContext.getAttribute("loggedUsers")).thenReturn(new HashSet<String>());
        Mockito.when(request.getParameter("email")).thenReturn(VALID_USER_EMAIL);
        Mockito.when(request.getParameter("pass")).thenReturn(USER_PASS);
        Mockito.when(request.getServletContext()).thenReturn(servletContext);
        Mockito.when(request.getSession()).thenReturn(httpSession);

        Assertions.assertTrue(authService.login(request));
    }

    @Test
    public void testLogin_invalidUser() {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        ServletContext servletContext = Mockito.mock(ServletContext.class);
        HttpSession httpSession = Mockito.mock(HttpSession.class);

        Mockito.when(servletContext.getAttribute("loggedUsers")).thenReturn(new HashSet<String>());
        Mockito.when(request.getParameter("email")).thenReturn(INVALID_USER_EMAIL);
        Mockito.when(request.getParameter("pass")).thenReturn(USER_PASS);
        Mockito.when(request.getServletContext()).thenReturn(servletContext);
        Mockito.when(request.getSession()).thenReturn(httpSession);

        Assertions.assertFalse(authService.login(request));
    }

    @Test
    public void testLogin_invalidAlreadyAuthorized() {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        ServletContext servletContext = Mockito.mock(ServletContext.class);
        HttpSession httpSession = Mockito.mock(HttpSession.class);

        HashSet<String> set = new HashSet<>();
        set.add(VALID_USER_EMAIL);

        Mockito.when(servletContext.getAttribute("loggedUsers")).thenReturn(set);
        Mockito.when(request.getParameter("email")).thenReturn(VALID_USER_EMAIL);
        Mockito.when(request.getParameter("pass")).thenReturn(USER_PASS);
        Mockito.when(request.getServletContext()).thenReturn(servletContext);
        Mockito.when(request.getSession()).thenReturn(httpSession);

        Assertions.assertFalse(authService.login(request));
    }

    @Test
    public void testLogout_valid() {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        ServletContext servletContext = Mockito.mock(ServletContext.class);
        HttpSession httpSession = Mockito.mock(HttpSession.class);

        HashSet<String> set = new HashSet<>();
        set.add(VALID_USER_EMAIL);

        Optional<User> user = Optional.of(new User.Builder().email(VALID_USER_EMAIL).build());

        Mockito.when(servletContext.getAttribute("loggedUsers")).thenReturn(set);
        Mockito.when(httpSession.getAttribute("user")).thenReturn(user);

        Mockito.when(request.getServletContext()).thenReturn(servletContext);
        Mockito.when(request.getSession()).thenReturn(httpSession);

        authService.logout(request);
        Assertions.assertTrue(set.isEmpty());
    }
}
