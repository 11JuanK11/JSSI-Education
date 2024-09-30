package project.jssi_education.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.jssi_education.entity.Semester;

@Repository
public interface ISemesterRepository extends JpaRepository<Semester, Long> {
}
