package com.cmpt276.ratecsteachingstaff.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class StaffRatingController {
    
    @GetMapping("/ratings/create")
    public String getMethodName(@RequestParam String param) {
        return new String();
    }
    
}
