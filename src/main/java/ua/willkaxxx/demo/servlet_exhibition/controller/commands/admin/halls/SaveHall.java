package ua.willkaxxx.demo.servlet_exhibition.controller.commands.admin.halls;

import ua.willkaxxx.demo.servlet_exhibition.controller.Regexp;
import ua.willkaxxx.demo.servlet_exhibition.controller.commands.Command;
import ua.willkaxxx.demo.servlet_exhibition.model.entity.Hall;
import ua.willkaxxx.demo.servlet_exhibition.model.services.HallService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class SaveHall implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        Hall hall = (Hall)session.getAttribute("editHall");
        String nextAddress = request.getParameter("address");
        if(!nextAddress.matches(Regexp.TEXT)){
            request.setAttribute("nAddress_error", true);
            request.getRequestDispatcher("/admin/editHall.jsp").forward(request,response);
            return;
        }
        hall.setAddress(nextAddress);
        new HallService().save(hall);
        response.sendRedirect("/exhibitions/admin/manageHalls?page=1");
    }
}
