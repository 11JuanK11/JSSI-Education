package project.jssi_education.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "schedule")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleId;
    private Course course;
    private String timeslot;  
    

}
