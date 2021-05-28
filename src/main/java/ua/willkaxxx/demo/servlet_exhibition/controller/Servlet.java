package ua.willkaxxx.demo.servlet_exhibition.controller;

import java.io.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Servlet", value = "*/")
public class Servlet extends HttpServlet {
    private String message;

//    public void init() {
//        message = "Hello World! 111!!!";
//    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{

    }

    public void destroy() {
    }
}