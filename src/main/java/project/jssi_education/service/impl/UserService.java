package project.jssi_education.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import project.jssi_education.entity.User;
import project.jssi_education.repository.IUserRepository;
import project.jssi_education.service.IUserService;

import java.util.List;

@Service
public class UserService implements IUserService{
    @Autowired
    private IUserRepository userRepository;

    @Override
    public User findbyId(Long id){
        for (User user: userRepository.findAll()){
            if (user.getId().equals(id)){
                return user;
            }
        }
        return null;
    }
    @Override
    public List<User> findAll(){
        return userRepository.findAll();
    }
    @Override
    public void insert(User user){
        userRepository.save(user);
    }

}
