package project.jssi_education.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.jssi_education.entity.Grade;

public interface IGradeRepository extends JpaRepository<Grade, Long> {
}
