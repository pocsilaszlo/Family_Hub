package tasks.familyhub.entity;

import jakarta.persistence.*;

@Entity
@Table(name="options")
public class UserOptions {

    @Id
    private String id;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    private String iconId;

    private String backGroundId;

    private Boolean darkMode;

    public UserOptions() {
        this.backGroundId = null;
        this.iconId = null;
        this.darkMode = false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getIconId() {
        return iconId;
    }

    public void setIconId(String iconId) {
        this.iconId = iconId;
    }

    public String getBackGroundId() {
        return backGroundId;
    }

    public void setBackGroundId(String backGroundId) {
        this.backGroundId = backGroundId;
    }

    public Boolean getDarkMode() {
        return darkMode;
    }

    public void setDarkMode(Boolean darkMode) {
        this.darkMode = darkMode;
    }

}
