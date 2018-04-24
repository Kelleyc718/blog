package io.chriskelley.blog.controllers;

import io.chriskelley.blog.models.User;
import io.chriskelley.blog.repos.UserRepo;
import io.chriskelley.blog.services.UserDetailsLoader;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private UserRepo userRepo;
    private PasswordEncoder encoder;

    public UserController(UserRepo userRepo, PasswordEncoder encoder) {
        this.userRepo = userRepo;
        this.encoder = encoder;
    }

    @GetMapping("/register")
    public String createUserForm() {
        return "/register";
    }

    // POST request used to submit values input by user
    @PostMapping("/register")
    public String createUser(@ModelAttribute User user) {
        String passHash = encoder.encode(user.getPassword());
        user.setPassword(passHash);
        userRepo.save(user);
        return "redirect:login";
    }


    @GetMapping("/login")
    public String loginForm() {
        Object loggedInUser = SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        if (loggedInUser.toString().equalsIgnoreCase("anonymousUser")) {
            return "/login";
        }
        return "redirect:/posts";
    }
}
