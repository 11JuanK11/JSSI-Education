package project.jssi_education.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.jssi_education.entity.Group;

@Repository
public interface IGroupRepository extends JpaRepository<Group, Long> {
}
