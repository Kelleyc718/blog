package com.example.clog.models;

import java.util.ArrayList;
import java.util.List;

public class Post implements Posts {
    private long id;
    private String username;
    private String title;
    private String body;

    public Post(String username, String title, String body) {
        this.username = username;
        this.title = title;
        this.body = body;
    }

    public Post(long id, String username, String title, String body) {
        this.id = id;
        this.username = username;
        this.title = title;
        this.body = body;
    }

    public static Post myPost() {
        String username = "Chris";
        String title = "Tough Love";
        String body = "I love my boy. But boy does he need a whooping sometimes! He is a hellion that loves to be mischievous!";
        return new Post(username, title, body);
    }

    public static Post myPost2() {
        String username = "PowderedToastMan";
        String title = "Love sugar powdered toast";
        String body = "As a lover and connoisseur of fine breakfast treats, nothing " +
                "gets my tighty whiteys in a bunch faster than powdered toast. " +
                "Sprinkle some sugar, a little cinnamon, maybe a splash of Canadian " +
                "amber...";
        return new Post(username, title, body);
    }

    public static List<Post> all() {
        List<Post> posts = new ArrayList<>();
        posts.add(myPost());
        posts.add(myPost2());
        return posts;
    }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getBody() { return body; }

    public void setBody(String body) { this.body = body; }
}
