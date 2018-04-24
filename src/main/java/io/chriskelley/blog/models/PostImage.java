package io.chriskelley.blog.models;

import javax.persistence.*;


@Entity
@Table(name = "images")
public class PostImage {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String path;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public PostImage(String path, Post post, long id) {
        this.path = path;
        this.post = post;
        this.id = id;
    }

    public PostImage(String path, Post post) {
        this.path = path;
        this.post = post;
    }

    public PostImage() { }

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public String getPath() { return path; }

    public void setPath(String path) { this.path = path; }

    public Post getPost() { return post; }

    public void setPost(Post post) { this.post = post; }
}
