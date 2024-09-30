package project.jssi_education.service;

import project.jssi_education.entity.User;

import java.util.List;

public interface IUserService {
    public User findById(Long id);

    public List<User> findAll();

    public User insert(User user);

    public String recoverPasswordByUsername(String username);
}
