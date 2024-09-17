package project.jssi_education.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.jssi_education.entity.Course;

@Repository
public interface ICourseRepository extends JpaRepository<Course, Long> {
}
