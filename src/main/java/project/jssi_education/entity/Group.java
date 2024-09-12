package project.jssi_education.entity;

import java.time.LocalTime;

import jakarta.persistence.Entity;

@Entity
public class Group {
    
    private int groupId;
    private Teacher teacher;
    private LocalTime starTime;
    private LocalTime endTime;
    private int numberStudents;
    private String classroom;
}
