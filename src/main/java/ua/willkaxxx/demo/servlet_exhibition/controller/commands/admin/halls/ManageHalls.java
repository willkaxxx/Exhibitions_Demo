package ua.willkaxxx.demo.servlet_exhibition.controller.commands.admin.halls;

import ua.willkaxxx.demo.servlet_exhibition.controller.commands.Command;
import ua.willkaxxx.demo.servlet_exhibition.model.services.ExhibitionService;
import ua.willkaxxx.demo.servlet_exhibition.model.services.HallService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 *  This class implements command pattern and shows manageHalls page
 */
public class ManageHalls implements Command {
    HallService hallService;

    public ManageHalls(HallService hallService){
        this.hallService = hallService;
    }
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Optional<String> page = Optional.ofNullable(request.getParameter("page"));
        request.setAttribute("halls", hallService.getPage(Integer.parseInt(page.orElse("1"))));
        request.setAttribute("totalPages", hallService.getTotalPages());
        request.getRequestDispatcher("/admin/manageHalls.jsp").forward(request,response);
    }
}
