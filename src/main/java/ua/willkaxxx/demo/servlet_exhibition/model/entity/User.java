package ua.willkaxxx.demo.servlet_exhibition.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    private int id;
    private String email;
    private boolean expired;
    private String password;
    private Role role;

    private List<Exhibition> exhibitions = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Exhibition> getExhibitions() {
        return exhibitions;
    }

    public void setExhibitions(List<Exhibition> exhibitions) {
        this.exhibitions = exhibitions;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", expired=" + expired +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", exhibitions number=" + exhibitions.size() +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof User)
            return this.toString().equals(obj.toString());
        return false;
    }
}
