package ua.willkaxxx.demo.servlet_exhibition.controller.commands.user;

import ua.willkaxxx.demo.servlet_exhibition.controller.commands.Command;
import ua.willkaxxx.demo.servlet_exhibition.model.services.ExhibitionService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class ShowHome implements Command {
    ExhibitionService exhibitionService = new ExhibitionService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Optional<String> page = Optional.ofNullable(request.getParameter("page"));
        Optional<String> orderBy = Optional.ofNullable(request.getParameter("orderBy"));
        Optional<String> dir = Optional.ofNullable(request.getParameter("dir"));
        request.setAttribute("exhibitions", exhibitionService.getPage(
                Integer.parseInt(page.orElse("1")),
                orderBy.orElse("1"),
                dir.orElse("asc")));
        request.setAttribute("totalPages", exhibitionService.getTotalPages());
        request.getRequestDispatcher("/index.jsp").forward(request,response);
    }
}
