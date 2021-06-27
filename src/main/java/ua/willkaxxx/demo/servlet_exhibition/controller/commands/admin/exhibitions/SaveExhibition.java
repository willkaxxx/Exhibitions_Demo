package ua.willkaxxx.demo.servlet_exhibition.controller.commands.admin.exhibitions;

import ua.willkaxxx.demo.servlet_exhibition.controller.commands.Command;
import ua.willkaxxx.demo.servlet_exhibition.controller.commands.InputDataValidator;
import ua.willkaxxx.demo.servlet_exhibition.model.entity.Exhibition;
import ua.willkaxxx.demo.servlet_exhibition.model.services.ExhibitionService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
/**
 *  This class implements command pattern and saves Exhibition
 */
public class SaveExhibition implements Command {
    ExhibitionService exhibitionService;

    public SaveExhibition(ExhibitionService exhibitionService){
        this.exhibitionService = exhibitionService;
    }
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Exhibition exhibition = (Exhibition) request.getSession().getAttribute("editExhibition");

        if(!InputDataValidator.validateExhibitionData(request)){
            request.getRequestDispatcher("/admin/editExhibition.jsp").forward(request,response);
            return;
        }

        exhibition.setName(request.getParameter("name"));
        exhibition.setSubject(request.getParameter("subject"));
        exhibition.setCost(new BigDecimal(request.getParameter("cost")));
        exhibition.setBeginning(request.getParameter("beginning"));
        exhibition.setEnd(request.getParameter("end"));

        exhibitionService.save(exhibition);
        response.sendRedirect("/exhibitions/admin/manageExhibitions?page=1");
    }
}
