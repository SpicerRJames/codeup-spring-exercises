package com.codeup.springblogapp.controllers;

import com.codeup.springblogapp.model.Ad;
import com.codeup.springblogapp.model.User;
import com.codeup.springblogapp.repositories.PostRepository;
import com.codeup.springblogapp.model.Post;
import com.codeup.springblogapp.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostExerciseController {

    private PostRepository post;
    private UserRepository userRepo;

    public PostExerciseController(PostRepository post, UserRepository userRepo) {
        this.post = post;
        this.userRepo = userRepo;
    }

//    @GetMapping("/posts")
//    public String showPostsIndexPage(Model model) {
//        List<Post> postList = post.findAll();
//        model.addAttribute("posts", post);
//        return "posts/index";
//    }

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
//    @GetMapping("/posts/{id}")
//    public String showAnIndividualPost(@PathVariable long id, Model model) {
//        Post aPost = post.getOne(id);
//        model.addAttribute("post", aPost);
//        return "posts/show";
//    }

    @GetMapping("/posts/post")
    public String sendPostForm(){
        return "posts/post";
    }

//    @GetMapping("/posts/create")
//    public String sendCreateForm(){
//        return "posts/create";
//    }
    @GetMapping("/posts/create")
    public String gotCreateForm(Model model){
        Post newPost = new Post();
        model.addAttribute("post", newPost);
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String createPost(@RequestParam String title, @RequestParam String description, Model viewModel){

        User user = userRepo.getOne(1L);
        Post newPost = new Post();
        newPost.setTitle(title);
        newPost.setDescription(description);
        //manually assign user to this post
        newPost.setUser(user);
        post.save(newPost);
//        viewModel.addAttribute("posts", post.findAll());
        return"redirect:/posts/index";
    }

    @GetMapping("/posts/index")
//    @ResponseBody
    public String index(Model viewModel) {
        viewModel.addAttribute("posts", post.findAll());
        return "posts/index";
    }

    @PostMapping("/posts/{user}/create")
    @ResponseBody
    public String newPost(
            @PathVariable User user,
            @RequestParam(name = "title") String titleParam,
            @RequestParam(name = "description") String descParam
    ) {
        User username = userRepo.getByUsername(user);
        username.setUsername("user1");
//        username += "<h1>" +

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


    @GetMapping("/posts/{id}")
    public String showOnePost(@PathVariable long id, Model model){
        Post posts = post.getOne(id);
        model.addAttribute("post", posts);
        return "posts/show";
    }

}
