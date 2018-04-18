package com.example.clog.controllers;

import com.example.clog.models.Post;
import com.example.clog.svc.PostSvc;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

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
    @ResponseBody
    public String createForm() {
        return "Form used to create posts";
    }

    @RequestMapping(value="/posts/create", method = POST)
    public String createPost() {
        return "success";
    }
}
