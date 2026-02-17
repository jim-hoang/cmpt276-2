package com.cmpt276.ratecsteachingstaff.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cmpt276.ratecsteachingstaff.models.StaffRating;

public interface StaffRatingRepository extends JpaRepository<StaffRating, Integer> {
        
}
