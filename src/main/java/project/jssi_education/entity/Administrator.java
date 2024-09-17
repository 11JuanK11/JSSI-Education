package project.jssi_education.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "administrator")
public class Administrator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
