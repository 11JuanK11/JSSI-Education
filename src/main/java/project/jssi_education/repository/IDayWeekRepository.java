package project.jssi_education.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.jssi_education.entity.DayWeek;

public interface IDayWeekRepository extends JpaRepository<DayWeek, Long> {
}
