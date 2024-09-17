package project.jssi_education.entity;

import java.util.Date;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data

public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attendanceId;
    private Date date;
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "group_has_student_id", nullable = false)
    private GroupStudent group_has_student;
}
