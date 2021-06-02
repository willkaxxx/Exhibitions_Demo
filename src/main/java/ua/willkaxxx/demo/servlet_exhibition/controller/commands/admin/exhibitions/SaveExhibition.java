package ua.willkaxxx.demo.servlet_exhibition.controller.commands.admin.exhibitions;

import ua.willkaxxx.demo.servlet_exhibition.controller.commands.Command;
import ua.willkaxxx.demo.servlet_exhibition.model.entity.Exhibition;
import ua.willkaxxx.demo.servlet_exhibition.model.services.ExhibitionService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;

public class SaveExhibition implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Exhibition exhibition = (Exhibition) request.getSession().getAttribute("editExhibition");

        exhibition.setName(new String(request.getParameter("name").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
        exhibition.setSubject(new String(request.getParameter("subject").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
        exhibition.setCost(new BigDecimal(request.getParameter("cost")));
        exhibition.setBeginning(request.getParameter("beginning"));
        exhibition.setEnd(request.getParameter("end"));

        new ExhibitionService().save(exhibition);
        response.sendRedirect("/exhibitions/admin/manageExhibitions?page=1");
    }
}
