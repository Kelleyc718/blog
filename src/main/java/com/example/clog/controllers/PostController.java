package com.example.clog.controllers;

import com.example.clog.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class PostController {
    @GetMapping("/posts/index")
    @ResponseBody
    public String index() {
        return "Index page";
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
