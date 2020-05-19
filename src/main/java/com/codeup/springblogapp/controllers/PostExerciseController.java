//package com.codeup.springblogapp.controllers;
//
//import com.codeup.springblogapp.model.User;
//import com.codeup.springblogapp.repositories.PostRepository;
//import com.codeup.springblogapp.model.Post;
//import com.codeup.springblogapp.repositories.UserRepository;
//import com.codeup.springblogapp.services.EmailService;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//@Controller
//public class PostExerciseController {
//
//    private PostRepository post;
//    private UserRepository userRepo;
//    private EmailService emailService;
//
//    public PostExerciseController(UserRepository userRepo, PostRepository post, EmailService emailService) {
//        this.userRepo = userRepo;
//        this.post = post;
//        this.emailService = emailService;
//    }
//
//
//
////    @GetMapping("/posts")
////    public String showPostsIndexPage(Model model) {
////        List<Post> postList = post.findAll();
////        model.addAttribute("posts", post);
////        return "posts/index";
////    }
//
//    @GetMapping("/posts")
//    @ResponseBody
//    public String findPostByTitle(){
//        String posts = "<ul>Post";
//        for (Post post : this.post.findAll()) {
//            posts += "<li>"+post.getTitle() + " by " + post.getDescription() + "</li>";
//        }
//        posts += "</ul>";
//        return posts;
//    }
////    @GetMapping("/posts/{id}")
////    public String showAnIndividualPost(@PathVariable long id, Model model) {
////        Post aPost = post.getOne(id);
////        model.addAttribute("post", aPost);
////        return "posts/show";
////    }
//
//    @GetMapping("/posts/post")
//    public String sendPostForm(){
//        return "posts/post";
//    }
//
////    @GetMapping("/posts/create")
////    public String sendCreateForm(){
////        return "posts/create";
////    }
//    @GetMapping("/posts/create")
//    public String gotCreateForm(Model model){
//        Post newPost = new Post();
//        model.addAttribute("post", newPost);
//        return "posts/create";
//    }
//
//    @PostMapping("/posts/create")
//    public String createPost(@RequestParam String title, @RequestParam String description, Model viewModel, Post posts){
//
//        User user = userRepo.getOne(1L);
//        Post newPost = new Post();
//        newPost.setTitle(title);
//        newPost.setDescription(description);
//        //manually assign user to this post
//        newPost.setUser(user);
//        post.save(newPost);
//        emailService.prepareAndSend(posts, "You created an ad",
//                "Title:"+post.getUser()+ "\nDescription:"+posts.getDescription());
////        viewModel.addAttribute("posts", post.findAll());
//        return"redirect:/posts/index";
//    }
//    /*
//    *  @PostMapping("/ads/create")
//    public RedirectView createAd(@ModelAttribute Ad ad) {
//        User user = userDao.getOne(1L);
//        ad.setUser(user);
//        adDao.save(ad);
//        emailService.prepareAndSend(ad, "You created an ad",
//                    "Title:"+ad.getTitle()+ "\nDescription:"+ad.getDescription());
//        return new RedirectView("/ads/" + ad.getId());
//    }
//}
//*/
//
//    @GetMapping("/posts/index")
////    @ResponseBody
//    public String index(Model viewModel) {
//        viewModel.addAttribute("posts", post.findAll());
//        return "posts/index";
//    }
//
//    @PostMapping("/posts/{user}/create")
//    @ResponseBody
//    public String newPost(
//            @PathVariable User user,
//            @RequestParam(name = "title") String titleParam,
//            @RequestParam(name = "description") String descParam
//    ) {
//        User username = userRepo.getByUsername(user);
//        username.setUsername("user1");
////        username += "<h1>" +
//
//        Post posts = new Post();
//        posts.setTitle(titleParam);
//        posts.setDescription(descParam);
//        this.post.save(posts);//this.___ is how I can access the PostRepository methods.
//        return "redirect:/posts";//try redirect:/posts
//    }
//
//    @GetMapping("/posts/delete")
//    @ResponseBody
//    public String sendDeleteForm(){
//        return "post";
//    }
//
//    @PostMapping("/posts/delete")
//    @ResponseBody
//    public String deletePost(@RequestParam(name = "id") long postId) {
//            this.post.deleteById(postId);
//            return "Post was deleted";
//    }
//
//    @GetMapping("/posts/{id}/edit")
//    public String postEditForm(@PathVariable long id, Model model) {
//        Post aPost = post.getOne(id);
//        model.addAttribute("post", aPost);
//        return "posts/edit";
//    }
////
//    @PostMapping("/posts/{id}/edit")
//    @ResponseBody
//    public String updatePost(@RequestParam(name = "title") String titleParam, @RequestParam(name = "description") String descParam, @PathVariable long id) {
////        System.out.println(titleParam);
////        System.out.println(descParam);
//        Post editPost = post.getOne(id);
//        editPost.setTitle(titleParam);
//        editPost.setDescription(descParam);
//        post.save(editPost);
//        return "redirect:/post";
//
//    }
//
//
//    @GetMapping("/posts/{id}")
//    public String showOnePost(@PathVariable long id, Model model){
//        Post posts = post.getOne(id);
//        model.addAttribute("post", posts);
//        return "posts/show";
//    }
//
//}

package com.codeup.springblogapp.controllers;

import com.codeup.springblogapp.model.Post;
import com.codeup.springblogapp.model.User;
import com.codeup.springblogapp.repositories.PostRepository;
import com.codeup.springblogapp.repositories.UserRepository;
import com.codeup.springblogapp.services.EmailService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostExerciseController {

    private UserRepository userDao; // this has all the functions of JpaRepository
    private PostRepository postDao; // this has all the functions of JpaRepository
    private EmailService emailService;

    public PostExerciseController(UserRepository userDao, PostRepository postDao, EmailService emailService) {
        this.userDao = userDao;
        this.postDao = postDao;
        this.emailService = emailService;
    }

    @GetMapping("/posts")
    public String showPostsIndexPage(Model model) {
        model.addAttribute("posts", postDao.findAll());
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String showAnIndividualPost(@PathVariable long id, Model model) {
        Post thisPost = postDao.getOne(id); // using JpaRepository.getOne, but for our Post objects
        model.addAttribute("post", thisPost);
        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String createPost(Model model) {
//        User user = userDao.getOne(1L);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(user.getEmail());
        Post post = new Post();
        post.setUser(user);
        model.addAttribute("post", post);
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String submitCreatePost(@ModelAttribute Post post) {
        User author = userDao.getOne(1L);
        post.setUser(author);
        post = postDao.save(post);
        emailService.prepareAndSend(post,"You have created a new post",
                "Your post \""+post.getTitle()+
                        "\" was successfully created.\nYou can see it at http://localhost:8080/posts/"+post.getId()+"\nThank you.");
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/edit")
    public String showEditForm(@PathVariable long id, Model model) {
        Post post = postDao.getOne(id);
        model.addAttribute("post", post);
        return "posts/edit";
    }

    @PostMapping("/posts/edit")
    public String editPost(@ModelAttribute Post post) {
        postDao.save(post);
        return "redirect:/posts/" + post.getId();
    }

    @GetMapping("/posts/editcreate")
    public String editCreatePost(Model model) {
        User user = userDao.getOne(1L);
        Post post = new Post();
        post.setUser(user);
        model.addAttribute("post", post);
        return "/posts/edit";
    }
}

