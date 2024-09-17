package project.jssi_education.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.jssi_education.entity.Teacher;

@Repository
public interface ITeacherRepository extends JpaRepository<Teacher, Long> {
}
