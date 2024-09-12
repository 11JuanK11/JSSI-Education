package project.jssi_education.entity;

import java.time.LocalTime;
import java.util.Date;
import java.util.HashMap;

import jakarta.persistence.Entity;

@Entity
public class Course {
    
    private int courseId;
    private String courseName;
    private HashMap<Date, LocalTime> offers;
}
