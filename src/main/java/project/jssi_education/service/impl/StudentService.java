package project.jssi_education.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jssi_education.entity.Student;
import project.jssi_education.entity.User;
import project.jssi_education.repository.IStudentRepository;
import project.jssi_education.repository.IUserRepository;
import project.jssi_education.service.IStudentService;

import java.util.List;

@Service
public class StudentService implements IStudentService {
    @Autowired
    private IStudentRepository repo;

    public Student FindbyId(Long id){
        for (Student student: repo.findAll()){
            if (student.getId().equals(id)){
                return student;
            }
        }
        return null;
    }

    public List<Student> FindAll(){
        return repo.findAll();
    }

    public void Insert(Student student){
        repo.save(student);
    }
}
