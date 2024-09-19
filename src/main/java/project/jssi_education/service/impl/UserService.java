package project.jssi_education.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jssi_education.entity.User;
import project.jssi_education.repository.IUserRepository;
import project.jssi_education.service.IUserService;

import java.util.List;

@Service
public class UserService implements IUserService{
    @Autowired
    private IUserRepository repo;
    @Override
    public User FindbyId(Long id){
        for (User user: repo.findAll()){
            if (user.getId().equals(id)){
                return user;
            }
        }
        return null;
    }
    @Override
    public List<User> FindAll(){
        return repo.findAll();
    }
    @Override
    public void Insert(User user){
        repo.save(user);
    }

}
