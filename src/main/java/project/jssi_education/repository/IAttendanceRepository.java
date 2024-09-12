package project.jssi_education.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.jssi_education.entity.Attendance;

public interface IAttendanceRepository extends JpaRepository<Attendance, Long> {
}
