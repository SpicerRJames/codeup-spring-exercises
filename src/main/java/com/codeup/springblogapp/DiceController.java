package com.codeup.springblogapp;

import com.codeup.springblogapp.model.Dice;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DiceController {

    //First I need to get the mapping for the dice home page
    @GetMapping("role-guess")
    public String userGuess(){
        //The return is supposed to contain the html file with out the ".html" without the slash in the front
        return ("roll_guess");
    }

    //Second, I need to get the actual guess the user made
    //TODO What is the purpose of the model again?
    @GetMapping("/roll_guess_once")
    public String getGuess(Model model){
        boolean isFirstGuess = true;
        model.addAttribute("first_guess", isFirstGuess);
        return("roll_single_guess");
    }

    //Create a mapping for the users guess and test against it.
    @GetMapping("dice-roll/{guess}")
    //TODO PathVariables... WHat are they again?
    public String getUserGuess(@PathVariable int guess, Model model){
        boolean isGuess = false;
        boolean correct = false;
        Dice dice = new Dice();
        dice.die = (int) ((Math.random() * 6) + 1);
        if (dice.die == guess){
            correct = true;
            dice.result = "You are correct";
        } else {
            dice.result = "You are incorrect";
        }
        model.addAttribute("first_guess", isGuess);
        model.addAttribute("dice", dice);

        return ("roll_single_guess");
    }

}
