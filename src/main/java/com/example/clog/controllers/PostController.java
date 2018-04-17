package com.example.clog.controllers;

import com.example.clog.models.Post;
import com.example.clog.models.Posts;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class PostController {
    @GetMapping("/posts/index")
    public String index(Model md) {
        List<Post> posts = Post.all();
        md.addAttribute("posts", posts);
        return "posts/index";
    }


    @GetMapping("/posts/show")
    public String individualPost(Model md) {
        Post post = Post.myPost();
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
    @ResponseBody
    public String createPost() {
        return "Post created";
    }
}
