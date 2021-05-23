package ua.willkaxxx.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "halls")
public class Hall {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column
    private String address;
    @ManyToMany(mappedBy = "halls")
    private List<Exhibition> exhibitions;

    public Hall(Long id){
        this.id = id;
    }

    public Hall(){
    }

    public String toString(){
        return id.toString() + " " + address;
    }
}
