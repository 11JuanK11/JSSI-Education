package project.jssi_education.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import project.jssi_education.entity.Grade;

import project.jssi_education.exception.ResourceNotFoundException;
import project.jssi_education.service.IGradeService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/grades")
public class GradeController {
    @Autowired
    private IGradeService gradeService;

    @GetMapping("/")
    public List<Grade> findAll(){
        return gradeService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findOne(@PathVariable Long id) {
        try {
            Grade grade = gradeService.findById(id);
            return ResponseEntity.ok(grade);
        } catch (ResourceNotFoundException ex) {
            String errorMessage = "Grade not found with id " + id;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

    @PostMapping("/")
    public ResponseEntity<String> insert(@RequestBody Grade grade) {
        try {
            gradeService.insert(grade);
            return new ResponseEntity<>("Grade successfully created.", HttpStatus.CREATED);

        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>("An error occurred while creating the Grade.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        try {
            gradeService.deleteById(id);
            return new ResponseEntity<>(Map.of("message", "Offer successfully deleted."), HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(Map.of("message", ex.getMessage()), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(Map.of("message", "Internal server error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> Update(@PathVariable Long id, @RequestBody Grade grade) {
        try {
            gradeService.update(id, grade);
            return new ResponseEntity<>("Grade successfully updated.", HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>("An error occurred while updating the Grade.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
