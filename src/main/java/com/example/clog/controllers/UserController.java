package com.example.clog.controllers;

// Imports required for controller, mostly managed through Spring Boot and ThymeLeaf
import com.example.clog.repos.UserRepo;
import com.example.clog.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    // UserRepo used to establish the connection between the repository and controller
    // for user objects.
    private final UserRepo userDao;

    public UserController(UserRepo userDao) {
        this.userDao = userDao;
    }

    // GET request used to create registration form
    @GetMapping("/registration")
    public String createUserForm(Model md) {
        md.addAttribute("user", new User());
        return "/registration";
    }

    // POST request used to submit values input by user
    @PostMapping("/registration")
    public String createUser(
            @RequestParam(name = "username") String username,
            @RequestParam(name = "password") String password,
            @RequestParam(name = "email") String email
        ) {
            User user = new User(username, password, email);
            userDao.save(user);
            return "redirect:posts";
    }
}
