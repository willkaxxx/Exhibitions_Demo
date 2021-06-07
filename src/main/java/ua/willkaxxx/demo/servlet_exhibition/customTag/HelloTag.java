package ua.willkaxxx.demo.servlet_exhibition.customTag;

import ua.willkaxxx.demo.servlet_exhibition.model.entity.User;

import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;
import java.io.*;
import java.util.Optional;

public class HelloTag extends SimpleTagSupport {
    Optional<User> user;

    public void doTag() throws IOException {
        JspWriter out = getJspContext().getOut();
        if (user != null && user.isPresent()){
            out.print(' ');
            out.println(user.get().getEmail());
        }
    }

    public void setUser(Optional<User> user) {
        this.user = user;
    }
}
