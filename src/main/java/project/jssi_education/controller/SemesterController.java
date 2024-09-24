package project.jssi_education.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.jssi_education.entity.Semester;
import project.jssi_education.exception.ResourceNotFoundException;
import project.jssi_education.service.ISemesterService;

import java.util.List;

@RestController
@RequestMapping("/semesters")
public class SemesterController {

    @Autowired
    private ISemesterService semesterService;

    @PostMapping
    public ResponseEntity<Semester> createSemester(@RequestBody Semester semester) {
        Semester createdSemester = semesterService.createSemester(semester);
        return ResponseEntity.ok(createdSemester);
    }

    @GetMapping
    public ResponseEntity<List<Semester>> getAllSemesters() {
        List<Semester> semesters = semesterService.getAllSemesters();
        return ResponseEntity.ok(semesters);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Semester> getSemesterById(@PathVariable Long id) {
        try {
            Semester semester = semesterService.getSemesterById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Semester not found with id " + id));
            return ResponseEntity.ok(semester);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateSemester(@PathVariable Long id, @RequestBody Semester semesterDetails) {
        try {
            Semester updatedSemester = semesterService.updateSemester(id, semesterDetails);
            return new ResponseEntity<>("Semester successfully updated.", HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>("An error occurred while updating the semester.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSemester(@PathVariable Long id) {
        try {
            semesterService.deleteSemester(id);
            return new ResponseEntity<>("Semester successfully deleted.", HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>("An error occurred while deleting the semester.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
