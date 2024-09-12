package project.jssi_education.entity;

import jakarta.persistence.Entity;

@Entity
public class Enrollment {
    
    private int enrolmentId;
    private Course course;
    private Student student;
}
