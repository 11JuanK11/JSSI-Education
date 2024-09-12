package project.jssi_education.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.jssi_education.entity.Student;

public interface IStudentRepository extends JpaRepository<Student, Long> {
}
