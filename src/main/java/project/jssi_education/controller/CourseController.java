package project.jssi_education.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.jssi_education.entity.Course;
import project.jssi_education.entity.DegreeCourse;
import project.jssi_education.exception.ResourceNotFoundException;
import project.jssi_education.service.ICourseService;
import project.jssi_education.service.IDegreeCourseService;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private ICourseService courseService;
    @Autowired
    private IDegreeCourseService degreeCourseService;


    @GetMapping("/")
    public ResponseEntity<List<Course>> getAllCourses() {
        return ResponseEntity.ok(courseService.findAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        Course course = courseService.findById(id);
        return ResponseEntity.ok(course);
    }


    @PostMapping("/")
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {

        Course createdCourse = courseService.createCourse(course);

        if (course.getDegreeCourses() != null) {
            for (DegreeCourse degreeCourse : course.getDegreeCourses()) {

                degreeCourse.setCourse(createdCourse);

                if (degreeCourse.getDegree() != null && degreeCourse.getCourse() != null) {
                    degreeCourseService.createDegreeCourse(degreeCourse);
                }
            }
        }
        return ResponseEntity.ok(createdCourse);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable Long id, @RequestBody Course course) {
        try {
            Course updatedCourse = courseService.updateCourse(id, course);
            return ResponseEntity.ok(updatedCourse);
        } catch (ResourceNotFoundException ex) {
            String errorMessage = "Course not found with id " + id;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long id) {
        try {
            courseService.deleteCourse(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException ex) {
            String errorMessage = "Course not found with id " + id;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage); 
        }
    }

}
