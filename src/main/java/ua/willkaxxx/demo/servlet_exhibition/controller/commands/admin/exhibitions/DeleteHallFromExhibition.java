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
 *  This class implements command pattern and deletes hall from Exhibition
 */
public class DeleteHallFromExhibition implements Command {
    ExhibitionService exhibitionService;

    public DeleteHallFromExhibition(ExhibitionService exhibitionService){
        this.exhibitionService = exhibitionService;
    }
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        exhibitionService.removeHall(
                Integer.parseInt(request.getParameter("Eid")),
                Integer.parseInt(request.getParameter("Hid")));

        response.sendRedirect("/exhibitions/admin/editExhibition?exhibitionId=" +
                ((Exhibition)request.getSession().getAttribute("editExhibition")).getId());
    }
}
