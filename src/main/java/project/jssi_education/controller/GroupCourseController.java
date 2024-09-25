package project.jssi_education.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.jssi_education.entity.GroupCourse;
import project.jssi_education.exception.ResourceNotFoundException;
import project.jssi_education.service.IGroupCourseService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/group-course/")
public class GroupCourseController {
    @Autowired
    private IGroupCourseService groupCourseService;

    @GetMapping("/")
    public List<GroupCourse> findAll(){
        return groupCourseService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findOne(@PathVariable Long id) {
        try {
            GroupCourse groupCourse = groupCourseService.findById(id);
            return new ResponseEntity<>(groupCourse, HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(Map.of("message", ex.getMessage()), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(Map.of("message", "Internal server error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    public ResponseEntity<String> insert(@RequestBody GroupCourse groupCourse) {
        try {
            groupCourseService.insert(groupCourse);
            return new ResponseEntity<>("Group Course successfully created.", HttpStatus.CREATED);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>("An error occurred while creating the Group Course.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        try {
            groupCourseService.deleteById(id);
            return new ResponseEntity<>(Map.of("message", "Group Course successfully deleted."), HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(Map.of("message", ex.getMessage()), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(Map.of("message", "Internal server error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> Update(@PathVariable Long id, @RequestBody GroupCourse groupCourse) {
        try {
            groupCourseService.update(id, groupCourse);
            return new ResponseEntity<>("Group Course successfully updated.", HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>("An error occurred while updating the Group Course.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
