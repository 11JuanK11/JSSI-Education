package project.jssi_education.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.jssi_education.entity.Course;

public interface ICourseRepository extends JpaRepository<Course, Long> {
}
