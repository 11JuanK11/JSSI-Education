package project.jssi_education.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.jssi_education.entity.Teacher;

public interface ITeacherRepository extends JpaRepository<Teacher, Long> {
}
