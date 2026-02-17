package com.cmpt276.ratecsteachingstaff.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.cmpt276.ratecsteachingstaff.models.StaffRating;
import com.cmpt276.ratecsteachingstaff.models.StaffRatingFactory;
import com.cmpt276.ratecsteachingstaff.repository.StaffRatingRepository;

@Service
public class StaffRatingService {
    
    @Autowired
    private StaffRatingRepository staffRatingRepository;

    //@Autowired
    private List<StaffRating> ratings = new ArrayList<>();

    public String getRatings(Model model) {
        
        ratings = staffRatingRepository.findAll();
        model.addAttribute("ratings", ratings);
        return "index";
    }

   public String addRating(String name, String email, String role, 
        int clarity, int niceness, int knowledgeableScore) {

        StaffRating rating = StaffRatingFactory.createRating(name, email, clarity, niceness, knowledgeableScore, role);
        staffRatingRepository.save(rating);
        return "redirect:/index";
   }


}
