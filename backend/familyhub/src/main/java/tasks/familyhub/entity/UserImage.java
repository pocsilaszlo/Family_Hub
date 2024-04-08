package tasks.familyhub.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;


@Data
@Entity
@Table(name="images")
public class UserImage {

    @Id
    @Column(name = "id")
    @UuidGenerator
    protected String id;

    protected String name;

    @Lob
    @Column(name = "photo", columnDefinition="BLOB")
    protected byte[] imageData;

    protected String type;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "userId")
    protected User user;

    private String category;

    public UserImage() {}
    public UserImage(String name, byte[] imageData, String type, String category) {
        this.name = name;
        this.imageData = imageData;
        this.type = type;
        this.category = category;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
}
