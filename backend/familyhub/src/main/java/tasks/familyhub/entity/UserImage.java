package tasks.familyhub.entity;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class UserImage {

    @ManyToOne
    @MapsId
    @JoinColumn(name = "user_id")
    protected User user;

    @Id
    @Column(name = "user_id")
    protected String id;

    protected String name;

    @Lob
    @Column(name = "photo", columnDefinition="BLOB")
    protected byte[] imageData;

    protected String type;

    public UserImage() {}
    public UserImage(String name, byte[] imageData, String type) {
        this.name = name;
        this.imageData = imageData;
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
}
