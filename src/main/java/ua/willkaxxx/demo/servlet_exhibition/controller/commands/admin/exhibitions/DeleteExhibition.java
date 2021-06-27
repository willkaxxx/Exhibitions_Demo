package ua.willkaxxx.demo.servlet_exhibition.controller.commands.admin.exhibitions;

import ua.willkaxxx.demo.servlet_exhibition.controller.commands.Command;
import ua.willkaxxx.demo.servlet_exhibition.model.entity.Exhibition;
import ua.willkaxxx.demo.servlet_exhibition.model.services.ExhibitionService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This class implements command pattern and deletes Exhibition
 */
public class DeleteExhibition implements Command {
    ExhibitionService exhibitionService;

    public DeleteExhibition(ExhibitionService exhibitionService) {
        this.exhibitionService = exhibitionService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        exhibitionService.delete(
                ((Exhibition) request.getSession().getAttribute("editExhibition")).getId());
        response.sendRedirect("/exhibitions/admin/manageExhibitions?page=1");
    }
}
