package project.jssi_education.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.jssi_education.entity.TeacherEvaluation;

public interface ITeacherEvaluationRepository extends JpaRepository<TeacherEvaluation, Long> {
}
