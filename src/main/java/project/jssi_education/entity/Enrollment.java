package project.jssi_education.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "enrollment")
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long enrolmentId;
    private Course course;
    private Student student;
}
