package io.chriskelley.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LandingController {
    @RequestMapping(value = {"/", "/home", "", "/index"})
    public String home() {
        return "/home";
    }
}
