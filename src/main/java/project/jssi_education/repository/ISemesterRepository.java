package project.jssi_education.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.jssi_education.entity.Manager;
import project.jssi_education.entity.Semester;

public interface ISemesterRepository extends JpaRepository<Semester, Long> {
}
