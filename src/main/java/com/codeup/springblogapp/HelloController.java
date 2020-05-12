package com.codeup.springblogapp;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("/")
    @ResponseBody
    public String hello(){
        return "index page";
    }
//
//    @GetMapping("/hello")
//    @ResponseBody
//    public String helloSpring(){
//        return "Hello from spring";
//    }
//
//    // ** PATH VARIABLES ** //
//    @GetMapping("/hi/{name}")
//    @ResponseBody
//    public String sayHi(@PathVariable String name){
//        return ("hi" + " " + name);
//    }
//
//    @GetMapping("/defined-ad/{id}")
//    @ResponseBody
//    public String showAd(@PathVariable long id){
//
//        long Add = id + 1;
//
//        return "Showing details for add with id: " + Add;
//    }
//
//    @GetMapping("/home")
//    public String welcome() {
//        return "home";
//    }

    @GetMapping("/hello/{name}")
    public String sayHello(@PathVariable String name, Model model) {
        model.addAttribute("name", name);
        return "hello";
    }

}
