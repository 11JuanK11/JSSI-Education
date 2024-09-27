package project.jssi_education.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jssi_education.entity.User;
import project.jssi_education.repository.IUserRepository;
import project.jssi_education.service.IUserService;
import project.jssi_education.util.PasswordUtil;

import java.util.List;

@Service
public class UserService implements IUserService {
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
    public User insert(User user) {
        user.setPassword(PasswordUtil.hashPassword(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public String recoverPasswordByUsername(String username) {
        User user = userRepository.findByUserName(username);
        if (user != null) {
            return user.getPassword();
        }
        return null;
    }


}
