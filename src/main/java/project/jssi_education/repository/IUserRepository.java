package project.jssi_education.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.jssi_education.entity.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    User findByUserName(String username);
}
