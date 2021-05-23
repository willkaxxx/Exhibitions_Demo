package ua.willkaxxx.demo.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data
@Entity
@Table(name = "exhibitions")
public class Exhibition implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_exhibition")
    private Long id;
    @Column(name = "exhibition_name", nullable = false)
    private String exhibitionName;
    @Column(name = "subject")
    private String subject;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(name = "beginning", nullable = false)
    private Date beginning = new Date();
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(name = "end", nullable = false)
    private Date end = new Date();
    @Column(name = "cost", nullable = false)
    private BigDecimal cost;
    @Column
    boolean canceled = false;
    @ManyToMany
    @JoinTable(
            name = "exhibition_hall",
            joinColumns = @JoinColumn(name = "id_exhibition"),
            inverseJoinColumns = @JoinColumn(name = "id_hall"))
    private List<Hall> halls;
    @ManyToMany(mappedBy = "exhibitions")
    private List<User> users;
    @Transient
    private List<Long> httpHallsID;

    public String getStringBeginning(){
        return beginning.toString().replace(' ', 'T').substring(0, 16);
    }
    public String getStringEnd(){
        return end.toString().replace(' ', 'T').substring(0, 16);
    }
}
