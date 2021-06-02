package ua.willkaxxx.demo.servlet_exhibition.controller.commands.admin.halls;

import ua.willkaxxx.demo.servlet_exhibition.controller.commands.Command;
import ua.willkaxxx.demo.servlet_exhibition.model.services.HallService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ManageHalls implements Command {
    HallService hallService = new HallService();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("halls", hallService.getPage(Integer.parseInt(request.getParameter("page"))));
        request.setAttribute("totalPages", hallService.getTotalPages());
        request.getRequestDispatcher("/admin/manageHalls.jsp").forward(request,response);
    }
}
