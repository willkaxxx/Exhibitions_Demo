package ua.willkaxxx.demo.servlet_exhibition.controller.commands.admin.exhibitions;

import ua.willkaxxx.demo.servlet_exhibition.controller.commands.Command;
import ua.willkaxxx.demo.servlet_exhibition.model.entity.Exhibition;
import ua.willkaxxx.demo.servlet_exhibition.model.entity.Hall;
import ua.willkaxxx.demo.servlet_exhibition.model.services.ExhibitionService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  This class implements command pattern and adds hall to Exhibition
 */
public class AddHallToExhibition implements Command {
    ExhibitionService exhibitionService;

    public AddHallToExhibition(ExhibitionService exhibitionService){
        this.exhibitionService = exhibitionService;
    }

    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        exhibitionService.addHall(
                Integer.parseInt(request.getParameter("Eid")),
                Integer.parseInt(request.getParameter("hallToAdd")));
        response.sendRedirect("/exhibitions/admin/editExhibition?exhibitionId=" +
                ((Exhibition)request.getSession().getAttribute("editExhibition")).getId());
    }
}
