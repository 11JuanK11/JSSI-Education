package project.jssi_education.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.jssi_education.entity.*;
import project.jssi_education.exception.ResourceNotFoundException;
import project.jssi_education.repository.IGroupRepository;
import project.jssi_education.service.impl.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/teachers")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @Autowired
    private UserService userService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private GradeService gradeService;

    @Autowired
    private IGroupRepository groupRepository;

    @Autowired
    private GroupCourseService groupCourseService;
    @GetMapping("/")
    public List<Teacher> findAll() {
        return teacherService.findAll();
    }

    @GetMapping("/{idNumber}")
    public ResponseEntity<?> findByIdNumber(@PathVariable int idNumber) {
        try {
            Teacher teacher = teacherService.findByTeacherIdNumber(idNumber);
            return new ResponseEntity<>(teacher, HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(Map.of("message", ex.getMessage()), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(Map.of("message", "Internal server error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> insert(@RequestBody Teacher teacher) {
        try {
            Teacher createdTeacher = teacherService.insert(teacher);
            return new ResponseEntity<>(createdTeacher, HttpStatus.CREATED);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            return new ResponseEntity<>("An error occurred while creating the teacher.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{idNumber}")
    public ResponseEntity<?> deleteByIdNumber(@PathVariable int idNumber) {
        try {
            teacherService.deleteByTeacherIdNumber(idNumber);
            return new ResponseEntity<>(Map.of("message", "Teacher successfully deleted."), HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(Map.of("message", ex.getMessage()), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(Map.of("message", "Internal server error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/{idNumber}")
    public ResponseEntity<?> update(@PathVariable int idNumber, @RequestBody Teacher teacher) {
        try {
            Teacher updatedTeacher = teacherService.update(idNumber, teacher);
            return new ResponseEntity<>(updatedTeacher, HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>("An error occurred while updating the teacher.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/teacher-group/{idNumber}")
    public List<Group> getAssociatedGroups(@PathVariable int idNumber){
        return groupService.getGroupsByTeacher(idNumber);
    }

    @GetMapping("/teacher-grade/{studentId}/{groupId}/{teacherId}")
    public Grade getStudentGrades(@PathVariable int studentId, @PathVariable Long groupId, @PathVariable int teacherId){
        List<Group> groups = groupService.getGroupsByTeacher(teacherId);
        List<GroupCourse>groupCourses = groupCourseService.getGroupsCourse(groups, null);
        return teacherService.getGradesForStudent(groupCourses, studentId);
    }
}
