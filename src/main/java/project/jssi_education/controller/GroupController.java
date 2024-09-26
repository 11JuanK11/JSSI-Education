package project.jssi_education.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.jssi_education.entity.Group;
import project.jssi_education.entity.Offer;
import project.jssi_education.entity.OfferDayWeek;
import project.jssi_education.entity.Teacher;
import project.jssi_education.exception.ResourceNotFoundException;
import project.jssi_education.service.IGroupService;
import project.jssi_education.service.IOfferDayWeekService;
import project.jssi_education.service.IOfferService;
import project.jssi_education.service.ITeacherService;

@RestController
@RequestMapping("/groups")
public class GroupController {

    @Autowired
    private IGroupService groupService;

    @Autowired
    private IOfferDayWeekService offerDayWeekService;

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
    public ResponseEntity<?> insert(@RequestBody Group group) {
        System.out.println("hola" + group.toString());
        try {
            OfferDayWeek offerDayWeek = offerDayWeekService.findById(group.getOfferDayWeek().getId());
            Teacher teacher = teacherService.findByTeacherIdNumber(group.getTeacher().getUser().getIdNumber());
            group.setOfferDayWeek(offerDayWeek);
            group.setTeacher(teacher);
            Group newGroup = groupService.insert(group);
            return new ResponseEntity<>(newGroup, HttpStatus.CREATED);
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
    public ResponseEntity<?> Update(@PathVariable Long id, @RequestBody Group group) {
        try {
            Group updateGroup = groupService.update(id, group);
            return new ResponseEntity<>(updateGroup, HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>("An error occurred while updating the Group.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
