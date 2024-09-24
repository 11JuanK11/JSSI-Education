package project.jssi_education.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.jssi_education.entity.GroupCourse;

public interface IGroupCourseRepository extends JpaRepository<GroupCourse, Long> {
}
