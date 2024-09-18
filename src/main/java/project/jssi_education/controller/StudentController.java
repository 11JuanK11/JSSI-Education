package project.jssi_education.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.jssi_education.entity.Degree;
import project.jssi_education.entity.Student;
import project.jssi_education.entity.User;
import project.jssi_education.service.IStudentService;
import project.jssi_education.service.impl.DegreeService;
import project.jssi_education.service.impl.StudentService;
import project.jssi_education.service.impl.UserService;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

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
    public void insert(@RequestBody Student student){
        User userAux = userService.FindbyId(student.getUser().getId());
        Degree degreeAux =degreeService.FindbyId(student.getDegree().getId());

        student.setUser(userAux);
        student.setDegree(degreeAux);

        studentService.Insert(student);
    }
}
