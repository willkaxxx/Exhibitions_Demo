package ua.willkaxxx.demo.servlet_exhibition.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    private int id;
    private String email;
    private boolean expired;
    private String password;
    private Role role = Role.User;

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

    public String getAuthority(){
        return role.name();
    }

    public boolean hasExhibition(int id){
        for(Exhibition e : exhibitions){
            if(e.getId()==id)
                return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", expired=" + expired +
                ", role=" + role +
                ", exhibitions number=" + exhibitions.size() +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof User)
            return this.id == ((User) obj).id;
        return false;
    }

    public static class Builder {
        private final User user;

        public Builder() {
            user = new User();
        }

        public User.Builder id(int id) {
            user.id = id;
            return this;
        }

        public User.Builder email(String email) {
            user.email = email;
            return this;
        }

        public User.Builder expired(boolean expired) {
            user.expired = expired;
            return this;
        }

        public User.Builder password(String password) {
            user.password = password;
            return this;
        }

        public User.Builder role(Role role) {
            user.role = role;
            return this;
        }

        public User build() {
            return user;
        }
    }
}
