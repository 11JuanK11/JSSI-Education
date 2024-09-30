package project.jssi_education.service;

import project.jssi_education.entity.Teacher;
import project.jssi_education.exception.ResourceNotFoundException;

import java.util.List;

public interface ITeacherService {
    Teacher findByTeacherIdNumber(int idNumber) throws ResourceNotFoundException;
    List<Teacher> findAll();
    Teacher insert(Teacher teacher) throws ResourceNotFoundException;

    void deleteByTeacherIdNumber(int idNumber) throws ResourceNotFoundException;
    Teacher update(int idNumber, Teacher teacher)throws ResourceNotFoundException;
}
