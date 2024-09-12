package project.jssi_education.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.jssi_education.entity.Administrator;

public interface IAdministratorRepository extends JpaRepository<Administrator, Long> {
}
