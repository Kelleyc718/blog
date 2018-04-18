package com.example.clog.svc;

import com.example.clog.models.Post;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostSvc {
    private List<Post> posts;

    public PostSvc() {
        posts = new ArrayList<>();
        createPost();
    }

    public List<Post> all() {
        return posts;
    }

    public Post findOne(long id) {
        return posts.get((int) (id - 1));
    }

    public Post save(Post post) {
        post.setId(posts.size() + 1);
        posts.add(post);
        return post;
    }

    private void createPost() {
        Post post = new Post("rockChick59", "Legal Representation", "Need a good " +
                "lawyer asap!");
        save(post);

        Post post2 = new Post("rockerDude62", "Kurt Cobains Mic", "Boom!");
        save(post2);

        Post post3 = new Post("JohnnyBoyLaw", "You must acquit!", "Right? Wrong? " +
                "Doesn't matter to me!");
        save(post3);

    }
}
