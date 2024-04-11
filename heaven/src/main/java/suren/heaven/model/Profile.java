package suren.heaven.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Profile {
    @Id
    @GeneratedValue
    private long id;
    private String fullName;
    private String bio;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Profile(long id, String fullName, String bio, User user) {
        this.id = id;
        this.fullName = fullName;
        this.bio = bio;
        this.user = user;
    }

    public Profile() {
    }
}