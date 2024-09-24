package project.jssi_education.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.jssi_education.entity.Degree;
import project.jssi_education.entity.DegreeCourse;

@Repository
public interface IDegreeCourseRepository extends JpaRepository <DegreeCourse, Long> {
}
