package project.jssi_education.entity;

import java.io.Serializable;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "grade")
public class Grade implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gradeId;
    
    private Float testOne;
    private Float testTwo;
    private Float followUp;
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "group_has_course_id", nullable = false)
    private GroupCourse groupHasCourse;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;
}
