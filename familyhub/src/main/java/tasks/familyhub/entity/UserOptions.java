package tasks.familyhub.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import static jakarta.persistence.CascadeType.ALL;

@Entity
@Table(name="options")
public class UserOptions {

    @Id
    //@Column(name = "id")
    //@UuidGenerator
    private String id;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    private Long iconId;

    private Long backGroundId;

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

    public Long getIconId() {
        return iconId;
    }

    public void setIconId(Long iconId) {
        this.iconId = iconId;
    }

    public Long getBackGroundId() {
        return backGroundId;
    }

    public void setBackGroundId(Long backGroundId) {
        this.backGroundId = backGroundId;
    }

    public Boolean getDarkMode() {
        return darkMode;
    }

    public void setDarkMode(Boolean darkMode) {
        this.darkMode = darkMode;
    }

    @Override
    public String toString() {
        return "UserOptions{" +
                "id='" + id + '\'' +
                ", iconId=" + iconId +
                ", backGroundId=" + backGroundId +
                ", darkMode=" + darkMode +
                '}';
    }
}
