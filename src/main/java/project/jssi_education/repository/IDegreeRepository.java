package project.jssi_education.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.jssi_education.entity.Attendance;
import project.jssi_education.entity.Degree;

public interface IDegreeRepository extends JpaRepository<Degree, Long> {
}
