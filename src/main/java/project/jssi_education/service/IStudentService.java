package project.jssi_education.service;

import project.jssi_education.entity.Student;
import project.jssi_education.exception.ResourceNotFoundException;

import java.util.List;

public interface IStudentService {
    public Student findById(Long id) throws ResourceNotFoundException ;
    public List<Student> FindAll();

    public Student insert(Student student) throws ResourceNotFoundException;

    public void deleteById(Long id) throws ResourceNotFoundException;
    public void deleteByIdNumber(int idNumber) throws ResourceNotFoundException;
    public void update(Long id, Student student) throws ResourceNotFoundException;
    public Student updateByIdNumber(int idNumber, Student student) throws ResourceNotFoundException;
    public Student findByIdNumber(int idNumber) throws ResourceNotFoundException;

}
