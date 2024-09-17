package project.jssi_education.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@Table(name = "degree_course")
public class DegreeCourse implements Serializable {
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
