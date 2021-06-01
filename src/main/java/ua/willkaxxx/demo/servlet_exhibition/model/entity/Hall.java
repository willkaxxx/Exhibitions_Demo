package ua.willkaxxx.demo.servlet_exhibition.model.entity;

import java.util.ArrayList;
import java.util.List;

public class Hall {
    private int id;
    private String address;

    private List<Exhibition> exhibitions = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Exhibition> getExhibitions() {
        return exhibitions;
    }

    public void setExhibitions(List<Exhibition> exhibitions) {
        this.exhibitions = exhibitions;
    }

    @Override
    public String toString() {
        return "Hall{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", exhibitions number=" + exhibitions.size() +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Hall)
            return this.toString().equals(obj.toString());
        return false;
    }

    public static class Builder{
        private final Hall hall;

        public Builder(){
            hall = new Hall();
        }
        public Builder id(int id){
            hall.id = id;
            return this;
        }
        public Builder address(String address){
            hall.address = address;
            return this;
        }
        public Hall build(){
            return hall;
        }
    }
}
