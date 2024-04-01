package tasks.familyhub.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="backgrounds")
public class UserBackGround extends UserImage{

    public UserBackGround() {
    }

    public UserBackGround(String name, byte[] imageData, String type) {
        super(name, imageData, type);
    }
}
