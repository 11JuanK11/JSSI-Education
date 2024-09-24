package project.jssi_education.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jssi_education.entity.DegreeCourse;
import project.jssi_education.exception.ResourceNotFoundException;
import project.jssi_education.repository.IDegreeCourseRepository;
import project.jssi_education.service.IDegreeCourseService;

import java.util.List;
@Service
public class DegreeCourseService implements IDegreeCourseService {

    @Autowired
    private IDegreeCourseRepository degreeCourseRepository;
    @Override
    public DegreeCourse createDegreeCourse(DegreeCourse degreeCourse) {
        return degreeCourseRepository.save(degreeCourse);
    }

    @Override
    public List<DegreeCourse> getAllDegreeCourses() {
        return degreeCourseRepository.findAll();
    }

    @Override
    public DegreeCourse updateDegreeCourse(Long id, DegreeCourse degreeCourseDetails) {
        DegreeCourse degreeCourse = degreeCourseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DegreeCourse not found with id " + id));

        degreeCourse.setDegree(degreeCourseDetails.getDegree());
        degreeCourse.setCourse(degreeCourseDetails.getCourse());

        return degreeCourseRepository.save(degreeCourse);
    }

    @Override
    public void deleteDegreeCourse(Long id) {
        DegreeCourse degreeCourse = degreeCourseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DegreeCourse not found with id " + id));

        degreeCourseRepository.delete(degreeCourse);
    }
}
