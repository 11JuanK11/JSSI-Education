package project.jssi_education.entity;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "course")
public class Course implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;
    private String courseName;

    @OneToMany(mappedBy = "course")
    private Set<DegreeCourse> degreeCourses;

    @ManyToOne
    @JoinColumn(name = "semester_id", nullable = false)
    private Semester semester;
}
