package com.example.clog.controllers;

import com.example.clog.models.Post;
import com.example.clog.svc.PostSvc;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class PostController {
    private final PostSvc postSvc;

    public PostController(PostSvc postSvc) {
        this.postSvc = postSvc;
    }

    @GetMapping("/posts")
    public String index(Model md) {
        List<Post> posts = postSvc.all();
        md.addAttribute("posts", posts);
        return "posts/index";
    }


    @GetMapping("/posts/show{id}")
    public String individualPost(@PathVariable long id, Model md) {
        Post post = postSvc.findOne(id);
        String title = post.getTitle();
        String body = post.getBody();
        String username = post.getUsername();
        md.addAttribute("title", title);
        md.addAttribute("body", body);
        md.addAttribute("username", username);
        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String createForm(Model md) {
        md.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String createPost(
        @RequestParam(name = "title") String title,
        @RequestParam(name = "body") String body
    ) {
        Post post = new Post("username", title, body);
        postSvc.save(post);
        return "redirect:";
    }

    @GetMapping("/posts/edit{id}")
    public String showEdit(@PathVariable long id, Model md) {
        md.addAttribute("post", postSvc.findOne(id));
        return "/posts/edit";
    }

    @PostMapping("/posts/edit")
    public String editPost(
            @RequestParam(name = "id") long id,
            @RequestParam(name = "title") String title,
            @RequestParam(name = "body") String body
            ) {
        Post post = postSvc.findOne(id);
        post.setTitle(title);
        post.setBody(body);
        return "redirect:";
    }
}
