package ua.willkaxxx.demo.servlet_exhibition.model.entity;

public enum OrderDir {
    asc, desc;

    public OrderDir get(boolean dir){
        if(dir)
            return asc;
        return desc;
    }
}
