package project.jssi_education.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.jssi_education.entity.Enrrollment;

public interface IEnrollmentRepository extends JpaRepository<Enrrollment, Long> {
}
