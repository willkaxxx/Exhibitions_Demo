package ua.willkaxxx.demo.servlet_exhibition.controller.filters;

import ua.willkaxxx.demo.servlet_exhibition.model.entity.Role;
import ua.willkaxxx.demo.servlet_exhibition.model.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

@WebFilter(filterName = "adminFilter", urlPatterns = {"/exhibitions/admin/*", "/admin/*"} )
public class AdminFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        Object buffer = request.getSession().getAttribute("user");
        if (buffer != null) {
            User user = ((Optional<User>) buffer).orElse(new User());
            if (Role.Admin.equals(user.getRole())) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        }

        servletRequest.getRequestDispatcher("/exhibitions/index.jsp").forward(servletRequest, servletResponse);
    }
}
