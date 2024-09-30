package project.jssi_education.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.jssi_education.entity.Attendance;
import project.jssi_education.entity.Grade;

import java.util.List;

@Repository
public interface IAttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findByStudentId(Long studentId);
}
