package io.chriskelley.controllers;

import io.chriskelley.models.User;
import io.chriskelley.models.UserWithRoles;
import io.chriskelley.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserController {

    private UserRepo userRepo;
    private PasswordEncoder encoder;

    public UserController(UserRepo userRepo, PasswordEncoder encoder) {
        this.userRepo = userRepo;
        this.encoder = encoder;
    }

    @GetMapping("/register")
    public String createUserForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }

    // POST request used to submit values input by user
    @PostMapping("/register")
    public String createUser(@Valid User user, Errors errors, Model model) {
        if(errors.hasErrors()) {
            model.addAttribute(errors);
            model.addAttribute(user);
            return "register";
        }

        String passHash = encoder.encode(user.getPassword());

        user.setPassword(passHash);
        userRepo.save(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginForm() {
        Object loggedInUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (loggedInUser.toString().equalsIgnoreCase("anonymousUser")) {
            return "login";
        }
        return "redirect:posts";
    }
}
