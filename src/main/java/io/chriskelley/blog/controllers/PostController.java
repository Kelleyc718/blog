package io.chriskelley.blog.controllers;

import io.chriskelley.blog.models.Post;
import io.chriskelley.blog.models.User;
import io.chriskelley.blog.repos.PostRepo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class PostController {
    private final PostRepo postDao;

    public PostController(PostRepo postDao) {
        this.postDao = postDao;
    }

    @GetMapping("/posts")
    public String index(Model model) {
        model.addAttribute("posts", postDao.findAll());
        return "posts/index";
    }

    @GetMapping("/posts/show={id}")
    public String individualPost(@PathVariable long id, Model model) {
        model.addAttribute("post", postDao.findOne(id));
        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String createForm(Model model) {
        model.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String createPost(@Valid Post post, Errors errors, Model model) {
        if(errors.hasErrors()) {
            model.addAttribute(errors);
            model.addAttribute(post);
            return "/posts/create";
        }

        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        post.setUser(loggedInUser);

        postDao.save(post);
        return "redirect:/posts";
    }

    @GetMapping("/posts/edit={id}")
    public String showEdit(@PathVariable long id, Model model) {
        model.addAttribute("post", postDao.findOne(id));
        return "/posts/edit";
    }

    @PostMapping("/posts/edit")
    public String editPost(@ModelAttribute Post post) {
        postDao.save(post);
        return "redirect:/posts";
    }

    @GetMapping("/posts/delete{id}")
    public String deletePost(@PathVariable long id, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("post", postDao.findOne(id));
        return "/posts/delete";
    }

    @PostMapping("/posts/delete")
    public String deleteConfirm(@ModelAttribute Post post) {
        postDao.delete(post);
        return "redirect:/posts";
    }
}
