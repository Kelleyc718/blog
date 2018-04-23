package io.chriskelley.blog.controllers;

import io.chriskelley.blog.models.User;
import io.chriskelley.blog.repos.UserRepo;
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

    /**
     * @param model creates new user to populate a blank form.
     * @return returns the registration url path.
     */
    @GetMapping("/registration")
    public String createUserForm(Model model) {
        model.addAttribute("user", new User());
        return "/registration";
    }

    // POST request used to submit values input by user
    @PostMapping("/registration")
    public String createUser(@ModelAttribute User user) {
        String passHash = encoder.encode(user.getPassword());
        user.setPassword(passHash);
        userRepo.save(user);
        return "redirect:login";
    }

}
