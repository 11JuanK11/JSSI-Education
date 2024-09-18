package project.jssi_education.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.jssi_education.entity.User;
import project.jssi_education.service.IUserService;
import project.jssi_education.service.impl.UserService;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public List<User> findAll(){
        return userService.FindAll();
    }

    @GetMapping("/{id}")
    public User findOne(@PathVariable Long id){
        return userService.FindbyId(id);
    }

    @PostMapping("/")
    public void insert(@RequestBody User user){
        userService.Insert(user);
    }





}
