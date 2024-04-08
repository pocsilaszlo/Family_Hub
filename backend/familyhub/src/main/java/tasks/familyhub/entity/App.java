package tasks.familyhub.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="apps")
public class App {

    @Id
    @UuidGenerator
    private String id;

    private String name;

    private String genre;

    @ManyToMany
    @JoinTable(
            name = "users_apps",
            joinColumns = @JoinColumn(name = "app_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users = new ArrayList<>();
    public App() { }

    public App(String name, String genre) {
        this.name = name;
        this.genre = genre;
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public void removeUser(User user) {this.users.remove(user);}

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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
