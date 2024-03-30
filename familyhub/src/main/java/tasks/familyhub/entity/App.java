package tasks.familyhub.entity;

import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
@Table(name="apps")
public class App {

    @Id
    @GeneratedValue
    private String id;

    @Column( nullable=false )
    private String name;

    private String genre;
    @ManyToMany( mappedBy = "apps")
    private ArrayList<User> users;
}
