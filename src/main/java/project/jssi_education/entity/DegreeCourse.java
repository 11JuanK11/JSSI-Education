package project.jssi_education.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class DegreeCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "degree_id")
    private Degree degree;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;


}
