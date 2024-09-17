package project.jssi_education.entity;

import java.util.ArrayList;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data

public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gradeId;
    
    private ArrayList<Float> score;
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "group_has_student_id", nullable = false)
    private GroupStudent group_has_student;
}
