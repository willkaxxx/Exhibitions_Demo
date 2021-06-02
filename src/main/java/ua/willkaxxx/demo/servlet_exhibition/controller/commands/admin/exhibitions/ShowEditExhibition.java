package ua.willkaxxx.demo.servlet_exhibition.controller.commands.admin.exhibitions;

import ua.willkaxxx.demo.servlet_exhibition.controller.commands.Command;
import ua.willkaxxx.demo.servlet_exhibition.model.entity.Exhibition;
import ua.willkaxxx.demo.servlet_exhibition.model.services.ExhibitionService;
import ua.willkaxxx.demo.servlet_exhibition.model.services.HallService;

import javax.servlet.http.HttpServletRequest;

public class ShowEditExhibition implements Command {
    ExhibitionService exhibitionService = new ExhibitionService();
    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().setAttribute("editExhibition", exhibitionService.getExhibition(
                Integer.parseInt(request.getParameter("exhibitionId"))).orElse(new Exhibition()));
        request.setAttribute("allHalls", new HallService().getAllHalls());
        return "forward:/admin/editExhibition.jsp";
    }
}
