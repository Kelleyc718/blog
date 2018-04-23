package io.chriskelley.blog.controllers;

import io.chriskelley.blog.models.Post;
import io.chriskelley.blog.models.User;
import io.chriskelley.blog.repos.PostRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PostController {
    private final PostRepo postDao;

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
        String username = post.getUser().getUsername();
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
        User user = new User();
        user.setId(1L);

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
        postDao.save(post);
        return "redirect:";
    }

    @GetMapping("/posts/delete{id}")
    public String deletePost(@PathVariable long id, Model md) {
        md.addAttribute("id", id);
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
