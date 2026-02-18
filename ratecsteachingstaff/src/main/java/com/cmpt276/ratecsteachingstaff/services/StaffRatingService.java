package com.cmpt276.ratecsteachingstaff.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

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

    private List<StaffRating> ratings = new ArrayList<>();

    public String getAllRatings(Model model) {
        
        ratings = staffRatingRepository.findAll();
        model.addAttribute("ratings", ratings);
        return "index";
    }

    public String getRating(Model model, int id) {
        StaffRating rating = staffRatingRepository.findById(id).orElse(null);
        model.addAttribute("rating", rating);
        return "ratings/detail";
    }

    public String getRatingForEdit(Model model, int id) {
        StaffRating rating = staffRatingRepository.findById(id).orElse(null);
        model.addAttribute("rating", rating);
        return "ratings/edit";
    }

   public String addRating(String name, String email, String role, String comment, 
        double clarity, double niceness, double knowledgeableScore) {

        StaffRating rating = StaffRatingFactory.createRating(name, email, clarity, niceness, knowledgeableScore, role, comment);
        staffRatingRepository.save(rating);
        return "redirect:/";
    }

    public String editRating(String name, String email, String role, String comment,
        double clarity, double niceness, double knowledgeableScore, int id) {
        
        StaffRating rating = staffRatingRepository.findById(id).orElse(null);
        rating.setName(name);
        rating.setEmail(email);
        rating.setClarity(clarity);
        rating.setNiceness(niceness);
        rating.setKnowledgeableScore(knowledgeableScore);
        rating.setRoleType(role);
        rating.setComment(comment);
        rating.setUpdatedAt(LocalDate.now());
        staffRatingRepository.save(rating);
        return "redirect:/";
    }

    public String getDeletePage(Model model, int id) {
        StaffRating rating = staffRatingRepository.findById(id).orElse(null);
        model.addAttribute("rating", rating);
        return "ratings/delete";
    }

    public String deleteRating(int id) {
        staffRatingRepository.deleteById(id);
        return "redirect:/";
    }

    public boolean isValidRating(String name, String email, String comment, double clarity,
        double niceness, double knowledgeableScore, Model model) {
        boolean valid = true;
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
                            "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern p = Pattern.compile(emailRegex);

        if (name.trim().length() < 2 || name.trim().length() > 60) {
            model.addAttribute("nameError", "* Name must be between 2 and 60 characters");
            valid = false;
        } else if(name.isBlank()) {
            model.addAttribute("nameError", "* Name cannot be blank");
            valid = false;
        }
        
        if(!p.matcher(email).matches()) {
            model.addAttribute("emailError", "* Email must be in a valid format");
            valid = false;
        }

        if (clarity < 1 || clarity > 10) {
            model.addAttribute("clarityError", "* Clarity must be between 1 and 10");
            valid = false;
        }

        if (niceness < 1 || niceness > 10) {
            model.addAttribute("nicenessError", "* Niceness must be between 1 and 10");
            valid = false;
        }

        if (knowledgeableScore < 1 || knowledgeableScore > 10) {
            model.addAttribute("knowledgeableError", "* Knowledgeable Score must be between 1 and 10");
            valid = false;
        }

        if (comment.trim().length() > 255) {
            model.addAttribute("commentError", "* Comment must be under or equal to 255 characters");
            valid = false;
        }
        
        return valid;   
    }

}
