package com.codeup.springblogapp;

import com.codeup.springblogapp.model.Ad;
import com.codeup.springblogapp.model.Dice;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RollDice extends Dice{

    public RollDice(boolean roll) {
        super(roll);
    }

    @GetMapping("/dice-roll")
    public String diceRoll() {
        Dice die = new Dice(false);
//        if(die.equals(new Dice(true))){
//            return "/dice-roll" + roll;
//        }
        return "dice";
    }

    @PostMapping("/dice-roll/{roll}")
    @ResponseBody
    public String rollClick(@RequestParam(name = "roll") String guess, Model model){


        int max = 6;
        int min = 1;

        int rolls = (int) (Math.random() * (max - min + 1) + min);


        return "dice-roll" + rolls;

    }

//    @PostMapping("/roll-dice/n")
//    @ResponseBody
//    public String startRole(@RequestParam(name = "roll") String roll, Dice dice){
//
//    }

}
