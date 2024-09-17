package project.jssi_education.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data

public class Student {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "degree_id")
    private Degree degree;

}
