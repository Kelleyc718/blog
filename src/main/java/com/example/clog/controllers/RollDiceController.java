package com.example.clog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

import static org.codehaus.groovy.runtime.typehandling.NumberMath.isInteger;

@Controller
public class RollDiceController {
    @GetMapping("/roll-dice")
    public String gameStart(){
        return "roll-dice";
    }

    @GetMapping("/roll-dice/{num}")
    public String numChosen(@PathVariable int num, Model md){
        String win = "You win! Do you want to play again?";
        String lose = "You're a loser!";
        String err = "You definitely screwed something up!";

        if (!isInteger(num)) {
            md.addAttribute("msg", err);
        }

        Random rand = new Random();
        int randNum = rand.nextInt(6) + 1;
        md.addAttribute("num", num);
        md.addAttribute("ranNum", randNum);
        if(num == randNum) {
            md.addAttribute("msg", win);
        } else {
            md.addAttribute("msg", lose);
        }
        return "roll-dice";
    }
}
