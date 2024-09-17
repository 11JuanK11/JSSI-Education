package project.jssi_education.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.jssi_education.entity.Attendance;
import project.jssi_education.entity.Degree;

@Repository
public interface IDegreeRepository extends JpaRepository<Degree, Long> {
}
