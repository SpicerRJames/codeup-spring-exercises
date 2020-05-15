//package com.codeup.springblogapp;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//@Controller
//public class MathController {
//
//    @GetMapping("/adding-exercise/{id}/{version}")
//    @ResponseBody
//    public String add(@PathVariable long id, @PathVariable long version){
//        long adding = id + version;
//        return "The total of id and version is: " + adding;
//    }
//
//    @GetMapping("{opertation}/{firstNum}/{keyword}/{secondNum}")
//    @ResponseBody
//    public String doMath(@PathVariable String operation, @PathVariable int firstNum, @PathVariable String keyword, @PathVariable int secondNum){
//        switch (operation){
//            case "add":
//                int sum;
//                sum = firstNum + secondNum;
//                return Integer.toString(sum);
//            case"subtract":
//                int diff;
//                diff = secondNum - firstNum;
//                return Integer.toString(diff);
//            case "multiply":
//                int product;
//                product = firstNum * secondNum;
//                return Integer.toString(product);
//            case "divide":
//                int div;
//                div = firstNum / secondNum;
//                return Integer.toString(div);
//
//        }
//        //which return?
//    }
//
//}
