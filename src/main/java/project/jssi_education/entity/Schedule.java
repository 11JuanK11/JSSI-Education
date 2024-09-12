package project.jssi_education.entity;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data

public class Schedule {
    
    private Long scheduleId;
    private Course course;
    private String timeslot;  
    

}
