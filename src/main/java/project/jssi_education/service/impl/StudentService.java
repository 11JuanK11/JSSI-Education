package project.jssi_education.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jssi_education.entity.Student;
import project.jssi_education.entity.User;
import project.jssi_education.exception.ResourceNotFoundException;
import project.jssi_education.repository.IDegreeRepository;
import project.jssi_education.repository.IStudentRepository;
import project.jssi_education.repository.IUserRepository;
import project.jssi_education.service.IStudentService;

import java.util.List;

@Service
public class StudentService implements IStudentService {
    @Autowired
    private IStudentRepository studentRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IDegreeRepository degreeRepository;

    @Override
    public Student FindbyId(Long id){
        for (Student student: studentRepository.findAll()){
            if (student.getId().equals(id)){
                return student;
            }
        }
        return null;
    }

    @Override
    public List<Student> FindAll(){
        return studentRepository.findAll();
    }

    @Override
    public void Insert(Student student) throws ResourceNotFoundException {
        if (student.getUser() == null || student.getUser().getId() == null) {
            throw new ResourceNotFoundException("User information is missing.");
        }

        User user = userRepository.findById(student.getUser().getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + student.getUser().getId()));

        if (student.getDegree() != null && student.getDegree().getId() != null) {
            degreeRepository.findById(student.getDegree().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Degree not found with id: " + student.getDegree().getId()));
        }

        student.setUser(user);
        studentRepository.save(student);
    }

}
