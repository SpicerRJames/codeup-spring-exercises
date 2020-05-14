package com.codeup.springblogapp;

import com.codeup.springblogapp.model.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostExerciseController {

    private final PostRepository post;


    public PostExerciseController(PostRepository post) {
        this.post = post;
    }

//    @GetMapping("/posts")


    @GetMapping("/posts")
    @ResponseBody
    public String findPostByTitle(){
        String posts = "<ul>Post";
        for (Post post : this.post.findAll()) {
            posts += "<li>"+post.getTitle() + " by " + post.getDescription() + "</li>";
        }
        posts += "</ul>";
        return posts;
    }

    @GetMapping("/posts/create")
    public String sendPostForm(){
        return "post";
    }

    @GetMapping("/posts/index")
    @ResponseBody
    public String index(Model viewModel) {
        viewModel.addAttribute("posts", post.findAll());
        return "here are the posts";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String newPost(
            @RequestParam(name = "title") String titleParam,
            @RequestParam(name = "description") String descParam
    ) {
        Post posts = new Post();
        posts.setTitle(titleParam);
        posts.setDescription(descParam);
        this.post.save(posts);//this.___ is how I can access the PostRepository methods.
        return "redirect:/posts";//try redirect:/posts
    }

    @GetMapping("/posts/delete")
    @ResponseBody
    public String sendDeleteForm(){
        return "post";
    }

    @PostMapping("/posts/delete")
    @ResponseBody
    public String deletePost(@RequestParam(name = "id") long postId) {
            this.post.deleteById(postId);
            return "Post was deleted";
    }

    @GetMapping("/posts/{id}/edit")
    public String postEditForm(@PathVariable long id, Model model) {
        Post aPost = post.getOne(id);
        model.addAttribute("post", aPost);
        return "posts/edit";
    }
//
    @PostMapping("/posts/{id}/edit")
    @ResponseBody
    public String updatePost(@RequestParam(name = "title") String titleParam, @RequestParam(name = "description") String descParam, @PathVariable long id) {
//        System.out.println(titleParam);
//        System.out.println(descParam);
        Post editPost = post.getOne(id);
        editPost.setTitle(titleParam);
        editPost.setDescription(descParam);
        post.save(editPost);
        return "redirect:/post";

    }



}
