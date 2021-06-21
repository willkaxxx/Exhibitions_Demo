package ua.willkaxxx.demo.servlet_exhibition.controller.filters;

import ua.willkaxxx.demo.servlet_exhibition.model.entity.Role;
import ua.willkaxxx.demo.servlet_exhibition.model.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebFilter(filterName = "authorizedFilter", urlPatterns = "/exhibitions/auth/*")
public class AuthorizedFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        Object buffer = request.getSession().getAttribute("user");
        User user = ((Optional<User>) buffer).orElse(new User());
        if (Role.Authorized.equals(user.getRole()) || Role.Admin.equals(user.getRole())) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        ((HttpServletResponse) servletResponse).sendRedirect("/exhibitions/index");
    }
}
