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
import project.jssi_education.service.IUserService;
import project.jssi_education.service.impl.DegreeService;
import project.jssi_education.service.impl.StudentService;
import project.jssi_education.service.impl.UserService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private IStudentService studentService;

    @Autowired
    private IUserService userService;

    @Autowired
    private DegreeService degreeService;

    @GetMapping("/")
    public List<Student> findAll(){
        return studentService.FindAll();
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<?> findOne(@PathVariable Long id) {
//        try {
//            Student student = studentService.FindbyId(id);
//            return new ResponseEntity<>(student, HttpStatus.OK);
//        } catch (ResourceNotFoundException ex) {
//            return new ResponseEntity<>(Map.of("message", ex.getMessage()), HttpStatus.NOT_FOUND);
//        } catch (Exception ex) {
//            return new ResponseEntity<>(Map.of("message", "Internal server error"), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @GetMapping("/{idNumber}")
    public ResponseEntity<?> findOneByIdNumber(@PathVariable int idNumber) {
        try {
            Student student = studentService.findByIdNumber(idNumber);
            return new ResponseEntity<>(student, HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(Map.of("message", ex.getMessage()), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(Map.of("message", "Internal server error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/")
    public ResponseEntity<?> insert(@RequestBody Student student) {
        try {
            if (student.getUser() == null) {
                return new ResponseEntity<>("User information is missing.", HttpStatus.BAD_REQUEST);
            }

            User userAux = userService.insert(student.getUser());
            student.setUser(userAux);

            Degree degreeAux = student.getDegree() != null ? degreeService.findById(student.getDegree().getId()) : null;
            student.setDegree(degreeAux);

            Student newStudent = studentService.insert(student);
            return new ResponseEntity<>(newStudent, HttpStatus.CREATED);

        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>("An error occurred while creating the student.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        try {
            studentService.deleteById(id);
            return new ResponseEntity<>(Map.of("message", "Student successfully deleted."), HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(Map.of("message", ex.getMessage()), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(Map.of("message", "Internal server error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/idnumber/{idNumber}")
    public ResponseEntity<?> deleteByIdNumber(@PathVariable int idNumber) {
        try {
            studentService.deleteByIdNumber(idNumber);
            return new ResponseEntity<>(Map.of("message", "Student successfully deleted."), HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(Map.of("message", ex.getMessage()), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(Map.of("message", "Internal server error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody Student student) {
        try {
            studentService.update(id, student);
            return new ResponseEntity<>("Student successfully updated.", HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>("An error occurred while updating the student.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/idnumber/{idNumber}")
    public ResponseEntity<String> updateByIdNumber(@PathVariable int idNumber, @RequestBody Student student) {
        try {
            studentService.updateByIdNumber(idNumber, student);
            return new ResponseEntity<>("Student successfully updated.", HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>("An error occurred while updating the student.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }





}
