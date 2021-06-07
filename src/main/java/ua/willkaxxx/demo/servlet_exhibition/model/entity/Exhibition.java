package ua.willkaxxx.demo.servlet_exhibition.model.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Exhibition {
    private int id;
    private String name;
    private String subject;
    private Timestamp beginning = Timestamp.valueOf(LocalDateTime.now());
    private Timestamp end = Timestamp.valueOf(LocalDateTime.now());
    private BigDecimal cost;
    private boolean canceled;

    private List<User> users = new ArrayList<>();
    private List<Hall> halls = new ArrayList<>();

    public int getId() {
        return id;    }

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

    public void setBeginning(String beginning) {
        this.beginning = Timestamp.valueOf(beginning + " 00:00:00");
    }

    public Timestamp getEnd() {
        return end;
    }

    public void setEnd(Timestamp end) {
        this.end = end;
    }

    public void setEnd(String end) {
        this.end = Timestamp.valueOf(end + " 00:00:00");
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

    public String getFormattedBeginning() {
        return dateFormat(beginning);
    }

    public String getFormattedEnd() {
        return dateFormat(end);
    }

    private String dateFormat(Timestamp date) {
        return date.toLocalDateTime().
                format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
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
                ", users number=" + users.size() +
                ", halls number=" + halls.size() +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Exhibition)
            return this.getId() == ((Exhibition) obj).getId();
        return false;
    }

    public static class Builder {
        private final Exhibition exhibition;

        public Builder() {
            exhibition = new Exhibition();
        }

        public Builder id(int id) {
            exhibition.id = id;
            return this;
        }

        public Builder name(String name) {
            exhibition.name = name;
            return this;
        }

        public Builder subject(String subject) {
            exhibition.subject = subject;
            return this;
        }

        public Builder cost(BigDecimal cost) {
            exhibition.cost = cost;
            return this;
        }

        public Builder beginning(Timestamp beginning) {
            exhibition.beginning = beginning;
            return this;
        }

        public Builder beginning(String beginning) {
            exhibition.setName(beginning);
            return this;
        }

        public Builder end(Timestamp end) {
            exhibition.end = end;
            return this;
        }

        public Builder end(String end) {
            exhibition.setEnd(end);
            return this;
        }

        public Exhibition build() {
            return exhibition;
        }
    }
}
