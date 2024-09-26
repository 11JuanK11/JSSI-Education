package project.jssi_education.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
public class DidacticMaterial implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String description;

    @ManyToOne
    @JoinColumn(name = "group_has_course_id", nullable = false)
    private GroupCourse group_has_course;
}
