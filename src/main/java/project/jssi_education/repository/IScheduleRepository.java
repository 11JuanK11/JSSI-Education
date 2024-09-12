package project.jssi_education.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.jssi_education.entity.Schedule;

public interface IScheduleRepository extends JpaRepository<Schedule, Long> {
}
