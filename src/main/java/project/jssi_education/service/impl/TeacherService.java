package project.jssi_education.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jssi_education.entity.Teacher;
import project.jssi_education.entity.User;
import project.jssi_education.exception.ResourceNotFoundException;
import project.jssi_education.repository.ITeacherRepository;
import project.jssi_education.repository.IUserRepository;
import project.jssi_education.service.ITeacherService;

import java.util.List;

@Service
public class TeacherService implements ITeacherService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private ITeacherRepository teacherRepository;

    @Override
    public Teacher findbyId(Long id) throws ResourceNotFoundException {
        return teacherRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Teacher not found with id: " + id));
    }

    @Override
    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    @Override
    public Teacher insert(Teacher teacher) throws ResourceNotFoundException {
        if (teacher.getUser() == null || teacher.getUser().getId() == null) {
            throw new ResourceNotFoundException("User information is missing.");
        }
        User user = userRepository.findById(teacher.getUser().getId()).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + teacher.getUser().getId()));
        teacher.setUser(user);

        return teacherRepository.save(teacher);
    }

    @Override
    public void deleteById(Long id) throws ResourceNotFoundException {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found with id: " + id));

        teacherRepository.delete(teacher);
    }

    @Override
    public Teacher update(Long id, Teacher teacher) throws ResourceNotFoundException {
        Teacher existingTeacher = findbyId(id);

        if (teacher.getUser() != null) {
            User userToUpdate = existingTeacher.getUser();

            if (teacher.getUser().getIdNumber()>0) {
                userToUpdate.setIdNumber(teacher.getUser().getIdNumber());
            }
            if (teacher.getUser().getName() != null) {
                userToUpdate.setName(teacher.getUser().getName());
            }
            if (teacher.getUser().getLastname() != null) {
                userToUpdate.setLastname(teacher.getUser().getLastname());
            }
            if (teacher.getUser().getUserName() != null) {
                userToUpdate.setUserName(teacher.getUser().getUserName());
            }
            if (teacher.getUser().getEmail() != null) {
                userToUpdate.setEmail(teacher.getUser().getEmail());
            }
            if (teacher.getUser().getPassword() != null) {
                userToUpdate.setPassword(teacher.getUser().getPassword());
            }

            existingTeacher.setUser(userToUpdate);
        }

        if (teacher.getSpecialization() != null) {
            existingTeacher.setSpecialization(teacher.getSpecialization());
        }

        return teacherRepository.save(existingTeacher);
    }




}
