package project.jssi_education.service;

import project.jssi_education.entity.Student;

import java.util.List;

public interface IStudentService {
    public Student FindbyId(Long id);
    public List<Student> FindAll();
    public void Insert(Student student);

}
