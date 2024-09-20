package project.jssi_education.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.jssi_education.entity.Degree;
import project.jssi_education.entity.DegreeCourse;

public interface IDegreeCourseRepository extends JpaRepository <DegreeCourse, Long> {
}
