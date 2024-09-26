package project.jssi_education.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.jssi_education.entity.Grade;

import java.util.List;

@Repository
public interface IGradeRepository extends JpaRepository<Grade, Long> {
    List<Grade> findByStudentId(Long studentId);
}
