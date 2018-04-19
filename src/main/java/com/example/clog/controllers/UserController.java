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
            @RequestParam(name = "id") long id,
            @RequestParam(name = "username") String username,
            @RequestParam(name = "password") String password,
            @RequestParam(name = "confPassword") String confPassword,
            @RequestParam(name = "firstname") String firstname,
            @RequestParam(name = "lastname") String lastname,
            @RequestParam(name = "email") String email
        ) {
        if(password.equalsIgnoreCase(confPassword)) {
            User user = new User(id, username, password, firstname, lastname, email);
            userDao.save(user);
            return "redirect:";
        } else {
            return "redirect: registration";
        }
    }
}
