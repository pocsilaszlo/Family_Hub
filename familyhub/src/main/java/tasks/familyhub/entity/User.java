package tasks.familyhub.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
public class User {

    @Id
    @Column(name = "id")
    @UuidGenerator
    private String id;

    private String name;

    private String password;

    private String role;

    //@OneToOne(mappedBy = "user")
    //@OneToOne(cascade = CascadeType.ALL)  // Kaszkád mentés a címhez
    //@JoinColumn(name = "options_id")
    //private UserOptions options;

    /*@ManyToMany
    @JoinTable(
            name = "users_apps",
            joinColumns = {@JoinColumn(name="user_id")},
            inverseJoinColumns = {@JoinColumn(name="app_id")}
    )*/
    //@ManyToMany(mappedBy = "users")
    @ElementCollection
    private List<App> apps = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private List<UserIcon> icons = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private List<UserBackGround> backGrounds = new ArrayList<>();

    public User() { }

    public User(String name, String password, String role) {
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

   /* public UserOptions getOptions() {
        return options;
    }

    public void setOptions(UserOptions options) {
        this.options = options;
    }
*/
    public List<App> getApps() {
        return apps;
    }
    public void setApps(List<App> apps) {
        this.apps = (ArrayList<App>) apps;
    }
    
    public void addApps(App app) {
        this.apps.add(app);
    }

    public List<UserIcon> getIcons() {
        return icons;
    }

    public void setIcons(ArrayList<UserIcon> icons) {
        this.icons = icons;
    }

    public List<UserBackGround> getBackGrounds() {
        return backGrounds;
    }

    public void setBackGrounds(ArrayList<UserBackGround> backGrounds) {
        this.backGrounds = backGrounds;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
               //", options=" + options.toString() +
                ", apps=" + apps.toString() +
                ", icons=" + icons.toString() +
                ", backGrounds=" + backGrounds.toString() +
                '}';
    }
}
