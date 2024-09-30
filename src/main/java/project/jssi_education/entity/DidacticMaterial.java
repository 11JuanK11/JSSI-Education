package project.jssi_education.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@Table(name = "didactic_material")
public class DidacticMaterial implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @ManyToOne
    @JoinColumn(name = "group_has_course_id", nullable = false)
    private GroupCourse groupHasCourse;
}
