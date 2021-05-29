package ua.willkaxxx.demo.servlet_exhibition.controller.commands;

import javax.servlet.http.HttpServletRequest;

public interface Command {
    String execute(HttpServletRequest request);
}
