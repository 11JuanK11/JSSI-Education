package project.jssi_education.entity;

import java.time.LocalTime;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "group")
public class Group {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long groupId;

    private Teacher teacher;
    private LocalTime starTime;
    private LocalTime endTime;
    private int numberStudents;
    private String classroom;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<GroupStudent> group_has_student;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Offer_id")
    private Offer offer;
}
