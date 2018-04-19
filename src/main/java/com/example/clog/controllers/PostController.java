package com.example.clog.controllers;

import com.example.clog.models.Post;
import com.example.clog.models.User;
import com.example.clog.repos.PostRepo;
import com.example.clog.repos.UserRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {
    private final PostRepo postDao;
    private UserRepo userDao;

    public PostController(PostRepo postDao) {
        this.postDao = postDao;
    }

    @GetMapping("/posts")
    public String index(Model md) {
        md.addAttribute("posts", postDao.findAll());
        return "posts/index";
    }


    @GetMapping("/posts/show{id}")
    public String individualPost(@PathVariable long id, Model md) {
        Post post = postDao.findOne(id);
        String title = post.getTitle();
        String body = post.getBody();
        String username = post.getUser().getUserName();
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
        User user = userDao.findOne(1L);
        Post post = new Post(title, body, user);
        postDao.save(post);
        return "redirect:";
    }

    @GetMapping("/posts/edit{id}")
    public String showEdit(@PathVariable long id, Model md) {
        md.addAttribute("post", postDao.findOne(id));
        return "/posts/edit";
    }

    @PostMapping("/posts/edit")
    public String editPost(
            @RequestParam(name = "id") long id,
            @RequestParam(name = "title") String title,
            @RequestParam(name = "body") String body
            ) {
        Post post = postDao.findOne(id);
        post.setTitle(title);
        post.setBody(body);
        return "redirect:";
    }

    @GetMapping("/posts/delete{id}")
    public String deletePost(@PathVariable long id, Model md) {
        md.addAttribute("post", postDao.findOne(id));
        return "/posts/delete";
    }

    @PostMapping("/posts/delete")
    public String deleteConfirm(
            @RequestParam(name = "id") long id
    ) {
        Post post = postDao.findOne(id);
        postDao.delete(post);
        return "redirect:";

    }
}
