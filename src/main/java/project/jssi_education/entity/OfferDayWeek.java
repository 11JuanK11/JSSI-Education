package project.jssi_education.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "offer_day_week")
public class OfferDayWeek {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "offer_id", nullable = false)
    private Offer offer;

    @ManyToOne
    @JoinColumn(name = "day_week_id", nullable = false)
    private DayWeek dayWeek;
}
