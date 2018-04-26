package io.chriskelley.blog.models;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    @NotEmpty(message = "Title cannot be left blank.")
    private String title;

    @Column(columnDefinition = "TEXT")
    @NotEmpty(message = "Body cannot be left blank.")
    private String body;

    @Column
    private String path;

    @OneToOne
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    private List<PostImage> Images;

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

    public List<PostImage> getImages() {
        return Images;
    }

    public void setImages(List<PostImage> Images) {
        this.Images = Images;
    }

    public String getPath() {
        return path;
    }

    public Post(String path) {
        this.path = path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}

