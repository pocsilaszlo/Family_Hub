package tasks.familyhub.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="backgrounds")
public class UserBackGround extends UserImage{

    public UserBackGround() {
    }

    public UserBackGround(User user, String name, byte[] imageData, String type) {
        super(user, name, imageData, type);
    }
}
