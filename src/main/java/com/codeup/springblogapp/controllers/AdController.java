package com.codeup.springblogapp.controllers;

import com.codeup.springblogapp.model.Ad;
import com.codeup.springblogapp.repositories.AdRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;


@Controller
public class AdController {

    private AdRepository adRepo;

    public AdController(AdRepository adRepo) {
        this.adRepo = adRepo;
    }

    @GetMapping("/ads")
    public String showAds(Model model){
        List<Ad> adList = new ArrayList<>();

        Ad ad = new Ad("Title", "Descrition");
        adList.add(ad);

        ad = new Ad("SecTitle", "SecDescription");
        adList.add(ad);

        model.addAttribute("ads", adList);
        return"ads/index";
    }


    @GetMapping("/ad")
    public String showAd(Model model){
        Ad ad = new Ad("Title", "Descrition");
        model.addAttribute("ads", ad);
        return"ads/index";
    }

    @GetMapping("/ads/create")
    public String gotCreateForm(Model model){
        Ad ad = new Ad();
        model.addAttribute("ad", ad);
        return "ads/create";
    }

//    @PostMapping("/ads/create")
//    public String showAds(@ModelAttribute Ad ad){
//        adRepo.save(ad);
//        return "ads/show";
//    }
}
