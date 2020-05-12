package com.codeup.springblogapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostController {

    @GetMapping("/posts")
    @ResponseBody
    public String posts(){
        //@PathVariable String post
        return "The title of this post is";
    }

    @GetMapping("/posts/{id}")
    @ResponseBody
    public String postId(@PathVariable String id){
        return "The post id is " + id;
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String create(){
        return "this is the page to create a post";
    }

    @PostMapping("posts/create")
    @ResponseBody
    public String createPost(){
        return "Here is where you would post the ad??";
    }
}
