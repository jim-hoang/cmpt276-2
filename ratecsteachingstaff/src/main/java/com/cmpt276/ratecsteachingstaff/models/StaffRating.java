package com.cmpt276.ratecsteachingstaff.models;

import java.time.LocalDate;

public class StaffRating {
    
    private String name;
    private String email;
    private int clarity;
    private int niceness;
    private int knowledgeableScore;
    private final LocalDate createdAt;
    private LocalDate updatedAt;
    private Role roleType;

    public enum Role {
        PROFESSOR,
        TA,
        STAFF,
    }
    
    // primary constructor
    public StaffRating(String name, String email, int clarity, int niceness, int knowledgeableScore, Role roleType) {
            this.name = name;
            this.email = email;
            this.clarity = clarity;
            this.niceness = niceness;
            this.knowledgeableScore = knowledgeableScore;
            this.createdAt = LocalDate.now();
            this.updatedAt = LocalDate.now();
            this.roleType = roleType;
    }

    // default constructor
    public StaffRating() {
        this.name = "null";
        this.email = "";
        this.clarity = 0;
        this.niceness = 0;
        this.knowledgeableScore = 0;
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
    }

    // accessor methods
    public String getName() { return name; }
    public String getEmail() { return email; }
    public int getClarity() { return clarity; }
    public int getNiceness() { return niceness; }
    public int getKnowledgeableScore() { return knowledgeableScore; }
    public LocalDate getCreatedAt() { return createdAt; }
    public LocalDate getUpdatedAt() { return updatedAt; }
    public Role getRoleType() { return roleType; }

    // setter methods
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setClarity(int clarity) { this.clarity = clarity; }
    public void setNiceness(int niceness) { this.niceness = niceness; }
    public void setKnowledgeableScore(int knowledgeableScore) { 
        this.knowledgeableScore = knowledgeableScore; 
    }
    public void setUpdatedAt(LocalDate date) { this.updatedAt = date; }
    public void setRoleType(Role roleType) { this.roleType = roleType; }
    
}
