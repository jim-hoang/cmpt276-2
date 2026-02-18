package com.cmpt276.ratecsteachingstaff.models;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="ratings")
public class StaffRating {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private double clarity;
    private double niceness;
    private double knowledgeableScore;
    private String comment;
    private final LocalDate createdAt;
    private LocalDate updatedAt;
    private Role roleType;

    public enum Role {
        PROFESSOR,
        INSTRUCTOR,
        TA,
        STAFF,
    }
    
    // primary constructor
    public StaffRating(String name, String email, double clarity, double niceness, 
        double knowledgeableScore, Role roleType, String comment) {
            this.name = name;
            this.email = email;
            this.clarity = clarity;
            this.niceness = niceness;
            this.knowledgeableScore = knowledgeableScore;
            this.createdAt = LocalDate.now();
            this.updatedAt = LocalDate.now();
            this.roleType = roleType;
            this.comment = comment;
    }

    // default constructor
    public StaffRating() {
        this.name = "John Doe";
        this.email = "johndoe@gmail.com";
        this.clarity = 1;
        this.niceness = 1;
        this.comment = "";
        this.knowledgeableScore = 1;
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
    }

    // accessor methods
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getComment() { return comment; }
    public double getClarity() { return clarity; }
    public double getNiceness() { return niceness; }
    public double getKnowledgeableScore() { return knowledgeableScore; }
    public LocalDate getCreatedAt() { return createdAt; }
    public LocalDate getUpdatedAt() { return updatedAt; }
    public Role getRoleType() { return roleType; }
    public int getId() { return id; }

    // setter methods
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setComment(String comment) { this.comment = comment; }
    public void setClarity(double clarity) { this.clarity = clarity; }
    public void setNiceness(double niceness) { this.niceness = niceness; }
    public void setKnowledgeableScore(double knowledgeableScore) { 
        this.knowledgeableScore = knowledgeableScore; 
    }
    public void setUpdatedAt(LocalDate date) { this.updatedAt = date; }
    public void setRoleType(String roleType) { 
        switch(roleType.toUpperCase()) {
            case "PROFESSOR" -> {
                this.roleType = Role.PROFESSOR;
            }
            case "INSTRUCTOR" -> {
                this.roleType = Role.INSTRUCTOR;
            }
            case "TA" -> {
                this.roleType = Role.TA;
            }
            default -> {
                this.roleType = Role.STAFF;
            }
        }
    }

    
}
