package project.jssi_education.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.jssi_education.entity.Manager;

public interface IManagerRepository extends JpaRepository<Manager, Long> {
}
