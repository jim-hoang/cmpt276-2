package com.cmpt276.ratecsteachingstaff.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cmpt276.ratecsteachingstaff.services.StaffRatingService;

@Controller
public class StaffRatingController {
    
    @Autowired
    private StaffRatingService staffRatingService;

    @GetMapping("/index")
    public String getAllRatings(Model model) {
        return staffRatingService.getRatings(model);
    }
    
    @PostMapping("/ratings/add")
    public String addRating(@RequestParam String name, @RequestParam String email, 
        @RequestParam String role, @RequestParam int clarity, @RequestParam int niceness,
        @RequestParam int knowledgeableScore) {
        
        return staffRatingService.addRating(name, email, role, clarity, niceness, knowledgeableScore);
    }

    @PostMapping("/ratings/edit")
    public String editRating(@RequestBody String entity) {
        //TODO: process POST request
        
        return "redirect:index";
    }
    
    
}
