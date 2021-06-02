package ua.willkaxxx.demo.servlet_exhibition.controller.commands.admin.halls;

import ua.willkaxxx.demo.servlet_exhibition.controller.commands.Command;
import ua.willkaxxx.demo.servlet_exhibition.model.entity.Hall;
import ua.willkaxxx.demo.servlet_exhibition.model.services.HallService;

import javax.servlet.http.HttpServletRequest;

public class ShowEditHall implements Command {
    HallService hallService = new HallService();
    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().setAttribute("editHall", hallService.getHall(
                Integer.parseInt(request.getParameter("hallId"))).orElse(new Hall()));
        return "forward:/admin/editHall.jsp";
    }
}
