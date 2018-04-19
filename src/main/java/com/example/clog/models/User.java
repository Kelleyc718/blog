package com.example.clog.models;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id @GeneratedValue
    private long id;

    @Column(nullable = false, length = 20)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 20)
    private String firstname;

    @Column(nullable = false, length = 20)
    private String lastname;

    @Column(nullable = false, length = 100)
    private String email;

    public User(long id, String username, String password, String firstname, String
            lastname, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }

    public User() { }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getFirstname() { return firstname; }

    public void setFirstname(String firstname) { this.firstname = firstname; }

    public String getLastname() { return lastname; }

    public void setLastname(String lastname) { this.lastname = lastname; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public long getUserId() { return id; }

    public void setUserId(long id) { this.id = id; }

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password;
    }
}





