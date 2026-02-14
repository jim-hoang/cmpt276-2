package com.cmpt276.ratecsteachingstaff.models;

public class StaffRatingFactory {
    
    public static StaffRating createRating(String name, String email, int clarity, int niceness, 
        int knowledgeableScore, String roleType) {
        
        switch(roleType.toUpperCase()) {
            case "PROFESSOR" -> {
                return new StaffRating(name, email, clarity, niceness,
                        knowledgeableScore, StaffRating.Role.PROFESSOR);
            }
            case "TA" -> {
                return new StaffRating(name, email, clarity, niceness,
                        knowledgeableScore, StaffRating.Role.TA);
            }
            case "STAFF" -> {
                return new StaffRating(name, email, clarity, niceness,
                        knowledgeableScore, StaffRating.Role.STAFF);
            } 
            default -> {
                return new StaffRating();
            }
        }
    }
}
