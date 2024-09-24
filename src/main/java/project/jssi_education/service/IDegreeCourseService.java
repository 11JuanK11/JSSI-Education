package project.jssi_education.service;

import project.jssi_education.entity.DegreeCourse;

import java.util.List;

public interface IDegreeCourseService {
    public DegreeCourse createDegreeCourse(DegreeCourse degreeCourse);
    public List<DegreeCourse> getAllDegreeCourses();

    DegreeCourse updateDegreeCourse(Long id, DegreeCourse degreeCourseDetails);
    void deleteDegreeCourse(Long id);
}
