package project.jssi_education.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.jssi_education.entity.Degree;
import project.jssi_education.entity.Student;
import project.jssi_education.entity.User;
import project.jssi_education.exception.ResourceNotFoundException;
import project.jssi_education.service.IStudentService;
import project.jssi_education.service.impl.DegreeService;
import project.jssi_education.service.impl.StudentService;
import project.jssi_education.service.impl.UserService;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private IStudentService studentService;

    @Autowired
    private UserService userService;

    @Autowired
    private DegreeService degreeService;

    @GetMapping("/")
    public List<Student> findAll(){
        return studentService.FindAll();
    }

    @GetMapping("/{id}")
    public Student findOne(@PathVariable Long id){
        return studentService.FindbyId(id);
    }

    @PostMapping("/")
    public ResponseEntity<String> insert(@RequestBody Student student) {
        try {
            if (student.getUser() == null || student.getUser().getId() == null) {
                return new ResponseEntity<>("User information is missing.", HttpStatus.BAD_REQUEST);
            }
            User userAux = userService.FindbyId(student.getUser().getId());
            Degree degreeAux = student.getDegree() != null ? degreeService.FindbyId(student.getDegree().getId()) : null;

            student.setUser(userAux);
            student.setDegree(degreeAux);
            studentService.Insert(student);
            return new ResponseEntity<>("Student successfully created.", HttpStatus.CREATED);

        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>("An error occurred while creating the student.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
