package project.jssi_education.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.HashSet;
import java.util.Set;

public class DayWeek {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String day;

    @ManyToMany(mappedBy = "dayWeeks")
    private Set<Offer> offers = new HashSet<>();
}
