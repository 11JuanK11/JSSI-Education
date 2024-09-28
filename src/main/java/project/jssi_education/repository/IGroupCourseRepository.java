package project.jssi_education.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.jssi_education.entity.GroupCourse;

import java.util.Optional;

public interface IGroupCourseRepository extends JpaRepository<GroupCourse, Long> {
    @Query("SELECT gc FROM GroupCourse gc WHERE gc.group.id = :groupId AND gc.course.id = :courseId")
    Optional<GroupCourse> findByGroupIdAndCourseId(@Param("groupId") Long groupId, @Param("courseId") Long courseId);

}
