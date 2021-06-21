package ua.willkaxxx.demo.servlet_exhibition.controller.commands.admin.exhibitions;

import ua.willkaxxx.demo.servlet_exhibition.controller.Regexp;
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

        String name = request.getParameter("name");
        String subject = request.getParameter("subject");

        boolean dataValid = true;

        if(!name.matches(Regexp.TEXT)){
            request.setAttribute("nName_error",true);
            dataValid = false;
        }
        if(!subject.matches(Regexp.TEXT)){
            request.setAttribute("nSubject_error",true);
            dataValid = false;
        }
        if(!request.getParameter("cost").matches(Regexp.DECIMAL)){
            request.setAttribute("nCost_error",true);
            dataValid = false;
        }
        if(request.getParameter("beginning").compareTo(request.getParameter("end")) > 0){
            request.setAttribute("nDate_error",true);
            dataValid = false;
        }
        if(!dataValid){
            request.getRequestDispatcher("/admin/editExhibition.jsp").forward(request,response);
            return;
        }

        exhibition.setName(name);
        exhibition.setSubject(subject);
        exhibition.setCost(new BigDecimal(request.getParameter("cost")));
        exhibition.setBeginning(request.getParameter("beginning"));
        exhibition.setEnd(request.getParameter("end"));

        new ExhibitionService().save(exhibition);
        response.sendRedirect("/exhibitions/admin/manageExhibitions?page=1");
    }
}
