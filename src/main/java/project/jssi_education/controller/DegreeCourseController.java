package project.jssi_education.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.jssi_education.entity.Course;
import project.jssi_education.entity.Degree;
import project.jssi_education.entity.DegreeCourse;
import project.jssi_education.exception.ResourceNotFoundException;
import project.jssi_education.repository.ICourseRepository;
import project.jssi_education.repository.IDegreeRepository;
import project.jssi_education.service.IDegreeCourseService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/degree-courses")
public class DegreeCourseController {
    @Autowired
    private IDegreeCourseService degreeCourseService;

    @Autowired
    private ICourseRepository courseRepository;

    @Autowired
    private IDegreeRepository degreeRepository;


    @PostMapping("/")
    public ResponseEntity<DegreeCourse> createDegreeCourse(@RequestBody DegreeCourse degreeCourse) {

        if (degreeCourse.getDegreeId() == null) {
            throw new ResourceNotFoundException("Degree ID cannot be null");
        }

        Optional<Degree> optionalDegree = degreeRepository.findById(degreeCourse.getDegreeId());

        if (!optionalDegree.isPresent()) {
            throw new ResourceNotFoundException("Degree with ID " + degreeCourse.getDegreeId() + " not found");
        }

        degreeCourse.setDegree(optionalDegree.get());

        if (degreeCourse.getCourseId() == null) {
            throw new ResourceNotFoundException("Course ID cannot be null");
        }

        Optional<Course> optionalCourse = courseRepository.findById(degreeCourse.getCourseId());

        if (!optionalCourse.isPresent()) {
            throw new ResourceNotFoundException("Course with ID " + degreeCourse.getCourseId() + " not found");
        }

        degreeCourse.setCourse(optionalCourse.get());

        DegreeCourse createdDegreeCourse = degreeCourseService.createDegreeCourse(degreeCourse);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDegreeCourse);
    }


    @GetMapping("/")
    public ResponseEntity<List<DegreeCourse>> getAllDegreeCourses() {
        List<DegreeCourse> degreeCourses = degreeCourseService.getAllDegreeCourses();
        return ResponseEntity.ok(degreeCourses);
    }


    @PutMapping("/{id}")
    public ResponseEntity<DegreeCourse> updateDegreeCourse(@PathVariable Long id, @RequestBody DegreeCourse degreeCourseDetails) {
        try {
            DegreeCourse updatedDegreeCourse = degreeCourseService.updateDegreeCourse(id, degreeCourseDetails);
            return ResponseEntity.ok(updatedDegreeCourse);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDegreeCourse(@PathVariable Long id) {
        try {
            degreeCourseService.deleteDegreeCourse(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
