package ua.willkaxxx.demo.servlet_exhibition.controller.commands.admin.halls;

import ua.willkaxxx.demo.servlet_exhibition.controller.commands.Command;
import ua.willkaxxx.demo.servlet_exhibition.model.entity.Hall;
import ua.willkaxxx.demo.servlet_exhibition.model.services.HallService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;

public class SaveHall implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Hall hall = (Hall)session.getAttribute("editHall");
        hall.setAddress(new String(request.getParameter("address").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
        new HallService().save(hall);
        return "redirect:/admin/adminHome.jsp";
    }
}