package project.jssi_education.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.jssi_education.entity.Group;

public interface IGroupRepository extends JpaRepository<Group, Long> {
}
