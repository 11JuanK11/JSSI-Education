package project.jssi_education.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
@Entity
@Table(name = "group_course")
public class GroupCourse implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToOne
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @OneToMany(mappedBy = "groupHasCourse", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<Attendance> attendances;

    @OneToMany(mappedBy = "groupHasCourse", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<Grade> grades;

    @OneToMany(mappedBy = "groupHasCourse", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<TeacherEvaluation> teacherEvaluations;

    @OneToMany(mappedBy = "groupHasCourse", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<DidacticMaterial> didacticMaterials;




}
