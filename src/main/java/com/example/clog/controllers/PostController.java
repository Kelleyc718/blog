package com.example.clog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class PostController {
    @GetMapping("/posts")
    @ResponseBody
    public String index() {
        return "Index page";
    }

    @GetMapping("/posts/{id}")
    @ResponseBody
    public String individualPost(@PathVariable long id) {
        return "Individual post view";
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
