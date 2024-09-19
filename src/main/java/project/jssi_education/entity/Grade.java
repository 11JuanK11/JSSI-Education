package project.jssi_education.entity;

import java.io.Serializable;
import java.util.ArrayList;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "grade")
public class Grade implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gradeId;
    
    private ArrayList<Float> score;
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "group_has_course_id", nullable = false)
    private GroupCourse group_has_course;
}
