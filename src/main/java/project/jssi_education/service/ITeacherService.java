package project.jssi_education.service;

import project.jssi_education.entity.Teacher;
import project.jssi_education.exception.ResourceNotFoundException;

import java.util.List;

public interface ITeacherService {
    Teacher findbyId(Long id) throws ResourceNotFoundException;
    List<Teacher> findAll();
    Teacher insert(Teacher teacher) throws ResourceNotFoundException;
    void deleteById(Long id) throws ResourceNotFoundException;
    Teacher update(Long id, Teacher teacher)throws ResourceNotFoundException;
}
