package ua.willkaxxx.demo.servlet_exhibition.controller.commands;

import ua.willkaxxx.demo.servlet_exhibition.controller.Regexp;

import javax.servlet.http.HttpServletRequest;

public class InputDataValidator {
    public static boolean validateCredentials(HttpServletRequest request){
        boolean dataValid = true;
        if (request.getParameter("email") != null && !request.getParameter("email").matches(Regexp.EMAIL)) {
            request.setAttribute("reg_email_error", true);
            dataValid = false;
        }
        if (request.getParameter("pass") != null &&  !request.getParameter("pass").matches(Regexp.PASSWORD)) {
            request.setAttribute("reg_pass_error", true);
            dataValid = false;
        }
        return dataValid;
    }
    public static boolean validateExhibitionData(HttpServletRequest request){
        boolean dataValid = true;

        if(!request.getParameter("name").matches(Regexp.TEXT)){
            request.setAttribute("nName_error",true);
            dataValid = false;
        }
        if(!request.getParameter("subject").matches(Regexp.TEXT)){
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
        return dataValid;
    }
}
