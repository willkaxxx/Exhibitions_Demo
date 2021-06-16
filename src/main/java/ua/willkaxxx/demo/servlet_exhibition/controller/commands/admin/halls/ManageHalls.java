package ua.willkaxxx.demo.servlet_exhibition.controller.commands.admin.halls;

import ua.willkaxxx.demo.servlet_exhibition.controller.commands.Command;
import ua.willkaxxx.demo.servlet_exhibition.model.services.HallService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class ManageHalls implements Command {
    HallService hallService = new HallService();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Optional<String> page = Optional.ofNullable(request.getParameter("page"));
        request.setAttribute("halls", hallService.getPage(Integer.parseInt(page.orElse("1"))));
        request.setAttribute("totalPages", hallService.getTotalPages());
        request.getRequestDispatcher("/admin/manageHalls.jsp").forward(request,response);
    }
}
