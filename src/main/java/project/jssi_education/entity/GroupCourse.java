package project.jssi_education.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "group_student")
public class GroupCourse implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @OneToMany(mappedBy = "group_has_student", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<Attendance> attendances;

    @OneToMany(mappedBy = "group_has_student", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<Grade> grades;

    @OneToMany(mappedBy = "group_has_student", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<TeacherEvaluation> teacher_evaluations;


}
