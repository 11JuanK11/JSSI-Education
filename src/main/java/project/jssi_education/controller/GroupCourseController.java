package project.jssi_education.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.jssi_education.entity.GroupCourse;
import project.jssi_education.exception.ResourceNotFoundException;
import project.jssi_education.service.impl.DegreeService;
import project.jssi_education.service.impl.GroupCourseService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/group-course/")
public class GroupCourseController {
    @Autowired
    private GroupCourseService groupCourseService;

    @Autowired
    private DegreeService degreeService;

    @GetMapping
    public List<GroupCourse> findAll(){
        return groupCourseService.findAll();
    }

    @GetMapping("{id}")
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

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody GroupCourse groupCourse) {
        try {
            GroupCourse newGroupCourse = groupCourseService.insert(groupCourse);
            return new ResponseEntity<>(newGroupCourse, HttpStatus.CREATED);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>("An error occurred while creating the Group Course.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("{id}")
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

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody GroupCourse groupCourse) {
        try {
            GroupCourse updateGroupCourse = groupCourseService.update(id, groupCourse);
            return new ResponseEntity<>(updateGroupCourse, HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>("An error occurred while updating the Group Course.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("degree/{id}")
    public ResponseEntity<?> findByDegree(@PathVariable Long id) {
        try {
            List<GroupCourse> groupCourse = groupCourseService.getGroupsCourseByDegree(degreeService.findById(id));
            return new ResponseEntity<>(groupCourse, HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(Map.of("message", ex.getMessage()), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(Map.of("message", "Internal server error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
