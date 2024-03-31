package tasks.familyhub.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="icons")
public class UserIcon extends UserImage{

    public UserIcon() {
    }

    public UserIcon(String name, byte[] imageData, String type) {
        super(name, imageData, type);
    }
}
