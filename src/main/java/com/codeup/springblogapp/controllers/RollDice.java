package com.codeup.springblogapp.controllers;

import com.codeup.springblogapp.model.Ad;
import com.codeup.springblogapp.model.Dice;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@Controller
public class RollDice{



    @GetMapping("/dice-roll")
    public String diceRoll(Model model) {
        model.addAttribute("roll", false);
//        if(die.equals(new Dice(true))){
//            return "/dice-roll" + roll;
//        }
        return "dice";
    }

    @GetMapping("/dice-rolled/{roll}")
    public String rollClick(@PathVariable int roll, Model model) {

        int max = 6;
        //int min = 1;
        //Math.random = 0.1 and 0.99999
        //.1 * 6 = 0.6
        //.999 * 6 = 5.994
        int guess = (int) (Math.random() * (max));

        if(roll == guess){
            model.addAttribute("correctGuess", true);

        }else{
            model.addAttribute("correctGuess", false);
        }

        model.addAttribute("roll", roll);
        model.addAttribute("guess", guess);

        //this will return the roll.html thymeleaf template
        return "roll";

    }

//    @PostMapping("/roll-dice/n")
//    @ResponseBody
//    public String startRole(@RequestParam(name = "roll") String roll, Dice dice){
//
//    }

}

////        Random rand = new Random();
////
////        int randInt = rand.nextInt(6);
////
////        model.addAttribute("hasRolled", true);
////
////        model.addAttribute("randNumber", randInt);
////
////        model.addAttribute("Guess", roll);
////
////        if (randInt == roll) {
////            model.addAttribute("message", "You are Correct");
////        } else {
////            model.addAttribute("message", "You are incorrect");
////        }
////
////
////    }