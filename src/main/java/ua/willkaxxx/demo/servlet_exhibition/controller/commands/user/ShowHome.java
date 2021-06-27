package ua.willkaxxx.demo.servlet_exhibition.controller.commands.user;

import ua.willkaxxx.demo.servlet_exhibition.controller.Regexp;
import ua.willkaxxx.demo.servlet_exhibition.controller.commands.Command;
import ua.willkaxxx.demo.servlet_exhibition.model.entity.Exhibition;
import ua.willkaxxx.demo.servlet_exhibition.model.entity.OrderDir;
import ua.willkaxxx.demo.servlet_exhibition.model.services.ExhibitionService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;
/**
 *  This class implements command pattern and shows home page
 */
public class ShowHome implements Command {
    ExhibitionService exhibitionService;

    public ShowHome(ExhibitionService exhibitionService){
        this.exhibitionService = exhibitionService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String[] sortParams;
        if(session.getAttribute("sortParams") == null){
            sortParams = new String[6];
            sortParams[0] = "cost";
            sortParams[1] = "asc";
        }
        else sortParams = (String[]) session.getAttribute("sortParams");

        putParam(request.getParameter("orderBy"), 0, sortParams);
        putParam(request.getParameter("dir"), 1, sortParams);
        putParam(request.getParameter("startBegin"), 2, sortParams);
        putParam(request.getParameter("stopBegin"), 3, sortParams);
        putParam(request.getParameter("startEnd"), 4, sortParams);
        putParam(request.getParameter("stopEnd"), 5, sortParams);

        Optional<String> page = Optional.ofNullable(request.getParameter("page"));
        request.setAttribute("exhibitions", exhibitionService.getPage(
                Integer.parseInt(page.orElse("1")),
                sortParams[0],
                OrderDir.valueOf(sortParams[1]),
                Optional.ofNullable(sortParams[2]),
                Optional.ofNullable(sortParams[3]),
                Optional.ofNullable(sortParams[4]),
                Optional.ofNullable(sortParams[5])));
        session.setAttribute("sortParams", sortParams);
        request.setAttribute("totalPages", exhibitionService.getTotalPagesFiltered(
                Optional.ofNullable(sortParams[2]),
                Optional.ofNullable(sortParams[3]),
                Optional.ofNullable(sortParams[4]),
                Optional.ofNullable(sortParams[5])));
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    private void putParam(String param, int place, String[] storage){
        if(param != null && param.length() == 0)
            storage[place] = null;
        if(param != null && (param.matches(Regexp.SQL_FIELD_NAME) || param.matches(Regexp.DATE)))
            storage[place] = param;
    }
}
