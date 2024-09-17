package project.jssi_education.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data

public class Teacher{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String specialization;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employment_contract_id")
    private EmploymentContract employmentContract;

    @ManyToOne
    @JoinColumn(name = "offer_id", nullable = false)
    private Offer offer;
}
