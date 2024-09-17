package project.jssi_education.entity;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "user_group")
public class Group implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long groupId;
<<<<<<< HEAD
    private Teacher teacher;
=======

>>>>>>> 22431c3d82a7a950465ddf9516c522241aae162c
    private LocalTime starTime;
    private LocalTime endTime;
    private int numberStudents;
    private String classroom;


    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<GroupStudent> group_has_student;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Offer_id")
    private Offer offer;
}
