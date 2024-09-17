package project.jssi_education.entity;

import java.time.LocalTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data

public class Course {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;
    private String courseName;
    private HashMap<Date, LocalTime> offers;

    @OneToMany(mappedBy = "course")
    private Set<DegreeCourse> degreeCourses;
}
