package project.jssi_education.service;

import project.jssi_education.entity.Course;

import java.util.List;

public interface ICourseService {
    public List<Course> findAll();
    public Course findById(Long id);
    public Course createCourse(Course course);
    public Course updateCourse(Long id, Course updatedCourse);
    public void deleteCourse(Long id);

}
