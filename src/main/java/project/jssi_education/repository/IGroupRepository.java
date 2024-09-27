package project.jssi_education.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project.jssi_education.entity.Course;
import project.jssi_education.entity.Group;
import project.jssi_education.entity.Teacher;

import java.util.List;

@Repository
public interface IGroupRepository extends JpaRepository<Group, Long> {
    List<Group> findByTeacher(Teacher teacher);

    @Query("SELECT gc.course FROM GroupCourse gc WHERE gc.group.id = :group_id")
    List<Course> findCoursesByGroupId(@Param("group_id") Long groupId);

}
