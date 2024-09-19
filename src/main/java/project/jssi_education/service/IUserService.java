package project.jssi_education.service;

import project.jssi_education.entity.User;

import java.util.List;

public interface IUserService {
    public User FindbyId(Long id);

    public List<User> FindAll();

    public void Insert(User user);
}
