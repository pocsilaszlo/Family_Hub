package tasks.familyhub.entity;

import jakarta.persistence.*;

@Entity
@Table(name="options")
public class UserOptions {

    @Id
    @Column(name = "user_id")
    private String id;
    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;
    private Long iconId;

    private Long backGroundId;

    private Boolean darkMode;
}
