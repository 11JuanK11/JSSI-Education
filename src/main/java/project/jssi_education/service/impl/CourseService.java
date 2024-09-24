package project.jssi_education.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jssi_education.entity.Course;
import project.jssi_education.entity.Semester;
import project.jssi_education.exception.ResourceNotFoundException;
import project.jssi_education.repository.ICourseRepository;
import project.jssi_education.repository.ISemesterRepository;
import project.jssi_education.service.ICourseService;

import java.util.List;

@Service
public class CourseService implements ICourseService {

    @Autowired
    private ICourseRepository courseRepository;

    @Autowired
    private ISemesterRepository semesterRepository;

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course findById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id " + id));
    }

    @Override
    public Course createCourse(Course course) {
        Semester semester = course.getSemester();
        Semester processedSemester;

        if (semester != null && semester.getId() != null) {
            processedSemester = semesterRepository.findById(semester.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Semester not found with id " + semester.getId()));
        } else if (semester != null) {
            processedSemester = semesterRepository.save(semester);
        } else {
            processedSemester = course.getSemester();
        }
        course.setSemester(processedSemester);
        return courseRepository.save(course);
    }


    @Override
    public Course updateCourse(Long id, Course updatedCourse) {
        Course existingCourse = findById(id);
        Semester semester = updatedCourse.getSemester();
        Semester processedSemester;

        if (semester != null && semester.getId() != null) {
            processedSemester = semesterRepository.findById(semester.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Semester not found with id " + semester.getId()));
        } else if (semester != null) {
            processedSemester = semesterRepository.save(semester);
        } else {
            processedSemester = existingCourse.getSemester();
        }
        existingCourse.setSemester(processedSemester);
        existingCourse.setCourseName(updatedCourse.getCourseName());
        return courseRepository.save(existingCourse);
    }

    @Override
    public void deleteCourse(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id " + id));
        courseRepository.delete(course);
    }




}
