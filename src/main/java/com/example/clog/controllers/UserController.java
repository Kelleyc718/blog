package com.example.clog.controllers;

import com.example.clog.repos.UserRepo;
import com.example.clog.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    private final UserRepo userDao;

    public UserController(UserRepo userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/registration")
    public String createUserForm(Model md) {
        md.addAttribute("user", new User());
        return "/registration";
    }

    @PostMapping("/registration")
    public String createUser(
            @RequestParam(name = "userId") long userId,
            @RequestParam(name = "username") String username,
            @RequestParam(name = "pass") String pass,
            @RequestParam(name = "confPass") String confPass,
            @RequestParam(name = "firstName") String firstName,
            @RequestParam(name = "lastName") String lastName,
            @RequestParam(name = "email") String email
        ) {
        if(pass.equalsIgnoreCase(confPass)) {
            User user = new User(userId, username, pass, firstName, lastName, email);
            userDao.save(user);
            return "redirect:";
        } else {
            return "redirect: registration";
        }
    }
}
