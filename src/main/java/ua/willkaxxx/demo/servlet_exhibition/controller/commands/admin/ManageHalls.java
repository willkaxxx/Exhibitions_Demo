package ua.willkaxxx.demo.servlet_exhibition.controller.commands.admin;

import ua.willkaxxx.demo.servlet_exhibition.controller.commands.Command;
import ua.willkaxxx.demo.servlet_exhibition.model.services.HallService;

import javax.servlet.http.HttpServletRequest;

public class ManageHalls implements Command {
    HallService hallService = new HallService();
    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("halls", hallService.getAllHalls());
        return "forward:/admin/manageHalls.jsp";
    }
}
