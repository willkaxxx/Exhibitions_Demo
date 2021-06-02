package ua.willkaxxx.demo.servlet_exhibition.controller.commands.admin.exhibitions;

import ua.willkaxxx.demo.servlet_exhibition.controller.commands.Command;
import ua.willkaxxx.demo.servlet_exhibition.model.services.ExhibitionService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ManageExhibitions implements Command {
    ExhibitionService exhibitionService = new ExhibitionService();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("exhibitions", exhibitionService.getPage(Integer.parseInt(request.getParameter("page"))));
        request.setAttribute("totalPages", exhibitionService.getTotalPages());
        request.getRequestDispatcher("/admin/manageExhibitions.jsp").forward(request, response);
    }
}
