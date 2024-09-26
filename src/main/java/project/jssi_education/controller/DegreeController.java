package project.jssi_education.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.jssi_education.entity.Degree;
import project.jssi_education.entity.User;
import project.jssi_education.exception.ResourceNotFoundException;
import project.jssi_education.service.IDegreeService;
import project.jssi_education.service.impl.DegreeService;

import java.util.List;

@RestController
@RequestMapping("/degrees")
public class DegreeController {
    @Autowired
    private IDegreeService degreeService;

    @GetMapping("/")
    public List<Degree> findAll(){
        return degreeService.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> findOne(@PathVariable Long id) {
        try {
            Degree degree = degreeService.findById(id);
            return ResponseEntity.ok(degree);
        } catch (ResourceNotFoundException ex) {
            String errorMessage = "Degree not found with id " + id;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }


    @PostMapping("/")
    public void insert(@RequestBody Degree degree){
        degreeService.insert(degree);
    }
}
