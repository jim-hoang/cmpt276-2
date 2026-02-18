package com.cmpt276.ratecsteachingstaff.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cmpt276.ratecsteachingstaff.services.StaffRatingService;

@Controller
public class StaffRatingController {
    
    @Autowired
    private StaffRatingService staffRatingService;

    @GetMapping("/")
    public String getAllRatings(Model model) {
        return staffRatingService.getAllRatings(model);
    }
    
    @GetMapping("/ratings/{id}")
    public String getRating(Model model, @PathVariable int id) {
        return staffRatingService.getRating(model, id);
    }

    @GetMapping("/ratings/add")
    public String addPage() {
        return "ratings/add";
    }

    @GetMapping("ratings/{id}/edit")
    public String getRatingForEdit(Model model, @PathVariable int id) {
        return staffRatingService.getRatingForEdit(model, id);
    }

    @GetMapping("/ratings/{id}/delete")
    public String deletePage(Model model, @PathVariable int id) {
        return staffRatingService.getDeletePage(model, id);
    }

    @PostMapping("/ratings/add")
    public String addRating(@RequestParam String name, @RequestParam String email, 
        @RequestParam String role, @RequestParam double clarity, @RequestParam double niceness,
        @RequestParam double knowledgeableScore, @RequestParam String comment, Model model) {
        
        if(staffRatingService.isValidRating(name, email, comment, clarity, niceness, knowledgeableScore, model)) {
            return staffRatingService.addRating(name, email, role, comment, clarity, niceness, knowledgeableScore);
        } else {
            return "ratings/add";
        }
    }

    @PostMapping("/ratings/{id}/edit")
    public String editRating(@RequestParam String name, @RequestParam String email,
        @RequestParam String comment, @RequestParam String role, @RequestParam double clarity,
        @RequestParam double niceness, @RequestParam double knowledgeableScore, @RequestParam int id, Model model) {
        
        if(staffRatingService.isValidRating(name, email, comment, clarity, niceness, knowledgeableScore, model)) {
            return staffRatingService.editRating(name, email, role, comment, 
            clarity, niceness, knowledgeableScore, id);
        } else {
            staffRatingService.getRatingForEdit(model, id);
            return "ratings/edit";
        }
    }

    @PostMapping("/ratings/delete")
    public String deleteRating(@RequestParam int id) {
        return staffRatingService.deleteRating(id);
    }
    
    
}
