package ua.willkaxxx.demo.servlet_exhibition.controller.commands.admin.exhibitions;

import ua.willkaxxx.demo.servlet_exhibition.controller.commands.Command;
import ua.willkaxxx.demo.servlet_exhibition.model.entity.Exhibition;
import ua.willkaxxx.demo.servlet_exhibition.model.entity.Hall;
import ua.willkaxxx.demo.servlet_exhibition.model.services.ExhibitionService;

import javax.servlet.http.HttpServletRequest;

public class DeleteHallFromExhibition implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        new ExhibitionService().removeHall(
                new Exhibition.Builder().id(Integer.parseInt(request.getParameter("Eid"))).build(),
                new Hall.Builder().id(Integer.parseInt(request.getParameter("Hid"))).build());
        return "redirect:/admin/adminHome.jsp";
    }
}