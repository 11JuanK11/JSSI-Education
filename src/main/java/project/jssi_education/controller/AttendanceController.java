package project.jssi_education.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.jssi_education.entity.Attendance;
import project.jssi_education.exception.ResourceNotFoundException;
import project.jssi_education.service.impl.AttendanceService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/attendances")
public class AttendanceController {
    @Autowired
    private AttendanceService attendanceService;

    @GetMapping("/")
    public List<Attendance> findAll(){
        return attendanceService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findOne(@PathVariable Long id) {
        try {
            Attendance attendance = attendanceService.findById(id);
            return ResponseEntity.ok(attendance);
        } catch (ResourceNotFoundException ex) {
            String errorMessage = "Grade not found with id " + id;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> insert(@RequestBody Attendance attendance) {
        try {
            Attendance attendanceSave = attendanceService.insert(attendance);
            return new ResponseEntity<>(attendanceSave, HttpStatus.CREATED);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>("An error occurred while creating the Attendance.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        try {
            attendanceService.deleteById(id);
            return new ResponseEntity<>(Map.of("message", "Offer successfully deleted."), HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(Map.of("message", ex.getMessage()), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(Map.of("message", "Internal server error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> Update(@PathVariable Long id, @RequestBody Attendance attendance) {
        try {
            Attendance updateAttendance = attendanceService.update(id, attendance);
            return new ResponseEntity<>(updateAttendance, HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>("An error occurred while updating the Grade.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
