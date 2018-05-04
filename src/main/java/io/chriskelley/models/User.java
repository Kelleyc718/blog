package io.chriskelley.models;

// Imports managed by Spring Boot and ThymeLeaf
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;

// Defines the table of 'User'
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true, nullable = false)
    @NotEmpty
    @Size(min=2, max=30, message = "Username is invalid.")
    private String username;

    @Column(nullable = false)
    @Size(min = 8, message = "Password must be a minimum of 8 characters," +
            " include upper and lowercase characters, and one number.")
    private String password;

    @Column(nullable = false, unique = true)
    private String email;


    public User() {
    }

    User(User copy) {
        id = copy.id;
        email = copy.email;
        username = copy.username;
        password = copy.password;
    }

    // Getter and Setter methods
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}






