package tasks.familyhub.entity;

import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue
    private String id;
    @Column( nullable=false )
    private String name;

    @Column( nullable=false )
    private String password;

    private String role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private UserOptions options;

    @ManyToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER )
    @JoinTable(
            name = "users_apps",
            joinColumns = {@JoinColumn(name="user_id")},
            inverseJoinColumns = {@JoinColumn(name="app_id")}
    )
    private ArrayList<App> apps;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private ArrayList<UserIcon> icons;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private ArrayList<UserBackGround> backGrounds;

}
