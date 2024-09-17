package project.jssi_education.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.jssi_education.service.IStudentService;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private IStudentService studentService;
}
