package ua.willkaxxx.demo.servlet_exhibition.model.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Exhibition {
    private int id;
    private String name;
    private String subject;
    private Timestamp beginning;
    private Timestamp end;
    private BigDecimal cost;
    private boolean canceled;

    private List<User> users = new ArrayList<>();
    private List<Hall> halls = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Timestamp getBeginning() {
        return beginning;
    }

    public void setBeginning(Timestamp beginning) {
        this.beginning = beginning;
    }

    public Timestamp getEnd() {
        return end;
    }

    public void setEnd(Timestamp end) {
        this.end = end;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Hall> getHalls() {
        return halls;
    }

    public void setHalls(List<Hall> halls) {
        this.halls = halls;
    }

    @Override
    public String toString() {
        return "Exhibition{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", subject='" + subject + '\'' +
                ", beginning=" + beginning +
                ", end=" + end +
                ", cost=" + cost +
                ", canceled=" + canceled +
//                ", users number=\n" + users +
//                ", halls number=\n" + halls+
                ", users number=" + users.size() +
                ", halls number=" + halls.size() +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Exhibition)
            return this.toString().equals(obj.toString());
        return false;
    }
}
