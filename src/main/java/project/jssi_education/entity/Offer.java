package project.jssi_education.entity;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "offer")
public class Offer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalTime startTime;
    private LocalTime endTime;

    @OneToMany(mappedBy = "offer", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<OfferDayWeek> offerDayWeeks;

}
