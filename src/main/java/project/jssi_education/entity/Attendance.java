package project.jssi_education.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "attendance")
public class Attendance implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attendanceId;
    private Date date;
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "group_has_course_id", nullable = false)
    private GroupCourse groupHasCourse;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;
}
