package project.jssi_education.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.jssi_education.entity.Group;
import project.jssi_education.entity.Offer;
import project.jssi_education.entity.OfferDayWeek;
import project.jssi_education.entity.Teacher;
import project.jssi_education.exception.ResourceNotFoundException;
import project.jssi_education.service.IGroupService;
import project.jssi_education.service.IOfferService;
import project.jssi_education.service.ITeacherService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/groups")
public class GroupController {

    @Autowired
    private IGroupService groupService;

    @Autowired
    private IOfferService offerService;

    @Autowired
    private ITeacherService teacherService;

    @GetMapping("/")
    public List<Group> findAll(){
        return groupService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findOne(@PathVariable Long id) {
        try {
            Group group = groupService.findById(id);
            return new ResponseEntity<>(group, HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(Map.of("message", ex.getMessage()), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(Map.of("message", "Internal server error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    public ResponseEntity<String> insert(@RequestBody Group group) {
        try {
            Offer offer = offerService.findById(group.getOffer().getId());
            Teacher teacher = teacherService.findByTeacherIdNumber(group.getTeacher().getUser().getIdNumber());
            group.setOffer(offer);
            group.setTeacher(teacher);
            groupService.insert(group);
            return new ResponseEntity<>("Group successfully created.", HttpStatus.CREATED);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>("An error occurred while creating the Group.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        try {
            groupService.deleteById(id);
            return new ResponseEntity<>(Map.of("message", "Group successfully deleted."), HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(Map.of("message", ex.getMessage()), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(Map.of("message", "Internal server error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> Update(@PathVariable Long id, @RequestBody Group group) {
        try {
            groupService.update(id, group);
            return new ResponseEntity<>("Group successfully updated.", HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>("An error occurred while updating the Group.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
