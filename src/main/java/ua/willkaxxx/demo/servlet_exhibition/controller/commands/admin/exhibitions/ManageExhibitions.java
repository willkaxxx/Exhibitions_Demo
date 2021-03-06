package ua.willkaxxx.demo.servlet_exhibition.controller.commands.admin.exhibitions;

import ua.willkaxxx.demo.servlet_exhibition.controller.commands.Command;
import ua.willkaxxx.demo.servlet_exhibition.model.services.ExhibitionService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
/**
 *  This class implements command pattern and shows manageExhibitions page
 */
public class ManageExhibitions implements Command {
    ExhibitionService exhibitionService;

    public ManageExhibitions(ExhibitionService exhibitionService){
        this.exhibitionService = exhibitionService;
    }
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Optional<String> page = Optional.ofNullable(request.getParameter("page"));
        request.setAttribute("exhibitions", exhibitionService.getPage(Integer.parseInt(page.orElse("1"))));
        request.setAttribute("totalPages", exhibitionService.getTotalPages());
        request.getRequestDispatcher("/admin/manageExhibitions.jsp").forward(request, response);
    }
}
