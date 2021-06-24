package ua.willkaxxx.demo.servlet_exhibition.controller.commands.admin.halls;

import ua.willkaxxx.demo.servlet_exhibition.controller.commands.Command;
import ua.willkaxxx.demo.servlet_exhibition.model.entity.Hall;
import ua.willkaxxx.demo.servlet_exhibition.model.services.HallService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowEditHall implements Command {
    HallService hallService;
    public ShowEditHall(HallService hallService){
        this.hallService = hallService;
    }
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getSession().setAttribute("editHall", hallService.getHall(
                Integer.parseInt(request.getParameter("hallId"))).orElse(new Hall()));
        request.getRequestDispatcher("/admin/editHall.jsp").forward(request,response);
    }
}
