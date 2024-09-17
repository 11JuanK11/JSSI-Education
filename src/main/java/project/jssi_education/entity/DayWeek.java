package project.jssi_education.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "day_week")
public class DayWeek {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String day;

    @ManyToMany(mappedBy = "dayWeeks")
    private Set<Offer> offers = new HashSet<>();
}
