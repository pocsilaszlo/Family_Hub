package tasks.familyhub.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="icons")
public class UserIcon extends UserImage{

    public UserIcon() {
    }

    public UserIcon(User user, String name, byte[] imageData, String type) {
        super(user, name, imageData, type);
    }
}
