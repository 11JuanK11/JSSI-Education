package project.jssi_education.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
public class Enrollment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long enrolmentId;
    private Course course;
    private Student student;
}
