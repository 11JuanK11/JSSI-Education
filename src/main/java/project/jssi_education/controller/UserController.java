package project.jssi_education.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.jssi_education.entity.User;
import project.jssi_education.exception.ResourceNotFoundException;
import project.jssi_education.service.impl.UserService;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public List<User> findAll(){
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User findOne(@PathVariable Long id){
        return userService.findById(id);
    }

    @PostMapping("/")
    public ResponseEntity<?> insert(@RequestBody User user){

        try {
            User createduser = userService.insert(user);
            return new ResponseEntity<>(createduser, HttpStatus.CREATED);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            return new ResponseEntity<>("An error occurred while creating the teacher.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
