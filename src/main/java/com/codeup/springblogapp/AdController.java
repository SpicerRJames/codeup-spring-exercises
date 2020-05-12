package com.codeup.springblogapp;

import com.codeup.springblogapp.model.Ad;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;


@Controller
public class AdController {

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
}
