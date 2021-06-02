package ua.willkaxxx.demo.servlet_exhibition.controller.commands.admin.exhibitions;

import ua.willkaxxx.demo.servlet_exhibition.controller.commands.Command;
import ua.willkaxxx.demo.servlet_exhibition.model.services.ExhibitionService;

import javax.servlet.http.HttpServletRequest;

public class ManageExhibitions implements Command {
    ExhibitionService exhibitionService = new ExhibitionService();
    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("exhibitions", exhibitionService.getPage(Integer.parseInt(request.getParameter("page"))));
        request.setAttribute("totalPages", exhibitionService.getTotalPages());
        return "forward:/admin/manageExhibitions.jsp";
    }
}
