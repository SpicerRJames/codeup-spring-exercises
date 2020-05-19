package com.codeup.springblogapp.controllers;

import com.codeup.springblogapp.model.Ad;
import com.codeup.springblogapp.model.User;
import com.codeup.springblogapp.repositories.AdRepository;
import com.codeup.springblogapp.repositories.UserRepository;
import com.codeup.springblogapp.services.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class AdController {

    private UserRepository userDao; // this has all the functions of JpaRepository
    private AdRepository adDao; // this has all the functions of JpaRepository
    private EmailService emailService;

    public AdController(UserRepository userDao, AdRepository adDao, EmailService emailService) {
        this.userDao = userDao;
        this.adDao = adDao;
        this.emailService = emailService;
    }

    @GetMapping("/ads")
    public String showAds(Model model) {
        model.addAttribute("ads", adDao.findAll());
        return "ads/index";
    }

    @GetMapping("/ads/{id}")
    public String showAd(@PathVariable long id, Model model) {
        Ad ad = adDao.getOne(id);
        model.addAttribute("ad", ad);
        return "ads/show";
    }

    @GetMapping("/ads/create")
    public String showCreateForm(Model model) {
        User user = userDao.getOne(1L);
        Ad ad = new Ad();
        ad.setUser(user);
        model.addAttribute("ad", ad);
        return "ads/create";
    }

    @PostMapping("/ads/create")
    public RedirectView createAd(@ModelAttribute Ad ad) {
        User user = userDao.getOne(1L);
        ad.setUser(user);
        adDao.save(ad);
        emailService.prepareAndSend(ad, "You created an ad",
                "Title:"+ad.getTitle()+ "\nDescription:"+ad.getDescription());
        return new RedirectView("/ads/" + ad.getId());
    }
}


//package com.codeup.springblogapp.controllers;
//
//import com.codeup.springblogapp.model.Ad;
//import com.codeup.springblogapp.model.AdBean;
//import com.codeup.springblogapp.model.Post;
//import com.codeup.springblogapp.model.User;
//import com.codeup.springblogapp.repositories.AdRepository;
//import com.codeup.springblogapp.services.EmailService;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//@Controller
//public class AdController {
//
//    private AdRepository adRepo;
//    private EmailService emailService;
//
//    public AdController(AdRepository adRepo) {
//        this.adRepo = adRepo;
//    }
//
//    @GetMapping("/ads")
//    public String showAds(Model model){
//        List<Ad> adList = new ArrayList<>();
//
//        Ad ad = new Ad("Title", "Descrition");
//        adList.add(ad);
//
//        ad = new Ad("SecTitle", "SecDescription");
//        adList.add(ad);
//
//        model.addAttribute("ads", adList);
//        return"ads/index";
//    }
//
//
//    @GetMapping("/ad")
//    public String showAd(Model model){
//        Ad ad = new Ad("Title", "Descrition");
//        model.addAttribute("ads", ad);
//        return"ads/index";
//    }
//
//    @GetMapping("/ads/create")
//    public String gotCreateForm(Model model){
//        Ad ad = new Ad();
//        model.addAttribute("ad", ad);
//        return "ads/create";
//    }
//
////    @PostMapping("/ads/create")
////    public String showAds(@ModelAttribute Ad ad){
////        adRepo.save(ad);
////        return "ads/show";
////    }
//
//
////    @PostMapping("/posts/create")
////    public String submitCreatePost(@ModelAttribute Post post) {
////        User author = adRepo.getOne(1L);
////        post.setUser(author);
////        post = postDao.save(post);
////        emailService.prepareAndSend(post,"You have created a new post",
////                "Your post \""+post.getTitle()+
////                        "\" was successfully created.\nYou can see it at http://localhost:8080/posts/"+post.getId()+"\nThank you.");
////        return "redirect:/posts";
////    }
//
//    @GetMapping("/ads/{id}")
//    public String showAd(@PathVariable long id, Model model) {
//        model.addAttribute("ad", adRepo.getOne(id));
//        return "ads/show";
//    }
//
//    @GetMapping("/ads/{id}/edit")
//    public String editAdForm(@PathVariable long id, Model model) {
//        Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        if (obj == null || !(obj instanceof UserDetails)) {
//            return "redirect:/login";
//        }
//        User user = (User) obj;
//        AdBean ad = adRepo.getOne(id);
//        if (ad.getUser().getId() != user.getId()) {
//            return "redirect:/ads/" + ad.getId();
//        }
//        model.addAttribute("ad", ad);
//        return "/ads/edit";
//    }
//    @PostMapping("/ads/{id}/edit")
//    public String editAdWithId(@PathVariable long id, @ModelAttribute AdBean ad) {
//        Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        if (obj == null || !(obj instanceof UserDetails)) {
//            return "redirect:/login";
//        }
//        User user = (User) obj;
//        ad.setId(id);
//        ad.setUser(user);
//        adRepo.save(ad);
//        emailService.prepareAndSend(ad, "EDITED Ad: " + ad.getTitle(),
//                ad.getTitle() +"\n\n" +
//                        ad.getDescription());
//        return "redirect:/ads/" + ad.getId();
//    }
//}
