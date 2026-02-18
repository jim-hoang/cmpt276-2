package com.cmpt276.ratecsteachingstaff.models;

public class StaffRatingFactory {
    
    public static StaffRating createRating(String name, String email, double clarity, double niceness, 
        double knowledgeableScore, String roleType, String comment) {
        
        switch(roleType.toUpperCase()) {
            case "PROFESSOR" -> {
                return new StaffRating(name, email, clarity, niceness,
                        knowledgeableScore, StaffRating.Role.PROFESSOR, comment);
            }
            case "TA" -> {
                return new StaffRating(name, email, clarity, niceness,
                        knowledgeableScore, StaffRating.Role.TA, comment);
            }
            case "INSTRUCTOR" -> {
                return new StaffRating(name, email, clarity, niceness,
                        knowledgeableScore, StaffRating.Role.INSTRUCTOR, comment);
            }
            default -> {
                return new StaffRating(name, email, clarity, niceness,
                        knowledgeableScore, StaffRating.Role.STAFF, comment);
            }
        }
    }
}
