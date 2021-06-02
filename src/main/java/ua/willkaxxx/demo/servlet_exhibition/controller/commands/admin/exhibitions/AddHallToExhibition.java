package ua.willkaxxx.demo.servlet_exhibition.controller.commands.admin.exhibitions;

import ua.willkaxxx.demo.servlet_exhibition.controller.commands.Command;
import ua.willkaxxx.demo.servlet_exhibition.model.entity.Exhibition;
import ua.willkaxxx.demo.servlet_exhibition.model.entity.Hall;
import ua.willkaxxx.demo.servlet_exhibition.model.services.ExhibitionService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddHallToExhibition implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println(new ExhibitionService().addHall(
                new Exhibition.Builder().id(Integer.parseInt(request.getParameter("Eid"))).build(),
                new Hall.Builder().id(Integer.parseInt(request.getParameter("hallToAdd"))).build()));
        response.sendRedirect("/exhibitions/admin/manageExhibitions?page=1");
    }
}
