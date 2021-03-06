package ua.willkaxxx.demo.servlet_exhibition.controller.commands.admin.exhibitions;

import ua.willkaxxx.demo.servlet_exhibition.controller.commands.Command;
import ua.willkaxxx.demo.servlet_exhibition.model.entity.Exhibition;
import ua.willkaxxx.demo.servlet_exhibition.model.services.ExhibitionService;
import ua.willkaxxx.demo.servlet_exhibition.model.services.HallService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 *  This class implements command pattern and shows editExhibition page
 */
public class ShowEditExhibition implements Command {
    ExhibitionService exhibitionService;

    public ShowEditExhibition(ExhibitionService exhibitionService){
        this.exhibitionService = exhibitionService;
    }
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getSession().setAttribute("editExhibition", exhibitionService.getExhibition(
                Integer.parseInt(request.getParameter("exhibitionId"))).orElse(new Exhibition()));
        request.setAttribute("allHalls", new HallService().getAllHalls());
        request.getRequestDispatcher("/admin/editExhibition.jsp").forward(request,response);
    }
}
