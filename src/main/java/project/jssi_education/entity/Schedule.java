package project.jssi_education.entity;

import jakarta.persistence.Entity;

@Entity
public class Schedule {
    
    private int schedule;
    private Course course;
    private String timeslot;  
    
}
