package project.jssi_education.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
@Entity
@Table(name = "day_week")
public class DayWeek implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String day;

    @OneToMany(mappedBy = "dayWeek", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<OfferDayWeek> offerDayWeeks;
}
