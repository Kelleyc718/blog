package io.chriskelley.blog.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String body;

    @OneToOne
    private User user;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "post_categories",
            joinColumns = {@JoinColumn(name = "post_id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id")}
    )
    private List<Categories> categories;

    public Post(String title, String body, User user, List<Categories> categories) {
        this.title = title;
        this.body = body;
        this.user = user;
        this.categories = categories;
    }

    public Post(String title, String body, User user) {
        this.title = title;
        this.body = body;
        this.user = user;
    }

    public Post() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Categories> getCategories() {
        return categories;
    }

    public void setCategories(List<Categories> categories) {
        this.categories = categories;
    }
}

