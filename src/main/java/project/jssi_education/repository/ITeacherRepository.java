package project.jssi_education.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.jssi_education.entity.Student;
import project.jssi_education.entity.Teacher;

import java.util.Optional;

@Repository
public interface ITeacherRepository extends JpaRepository<Teacher, Long> {
    @Query("SELECT t FROM Teacher t WHERE t.user.idNumber = ?1")
    Optional<Teacher> findByTeacherIdNumber(int idNumber);

    Teacher findByUserId(Long userId);
}
