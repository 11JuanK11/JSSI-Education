package project.jssi_education.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jssi_education.entity.*;
import project.jssi_education.exception.ResourceNotFoundException;
import project.jssi_education.repository.IGroupRepository;
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

    @Autowired
    private StudentService studentService;

    @Autowired
    private GradeService gradeService;

    @Autowired
    private IGroupRepository groupRepository;

    @Autowired
    private GroupCourseService groupCourseService;

    @Override
    public Teacher findByTeacherIdNumber(int idNumber) throws ResourceNotFoundException {
        return teacherRepository.findByTeacherIdNumber(idNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found with idNumber: " + idNumber));
    }

    @Override
    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    @Override
    public Teacher insert(Teacher teacher) {
        if (teacher.getUser() == null) {
            throw new ResourceNotFoundException("User information is missing.");
        }

        User newUser = teacher.getUser();
        newUser.setRole("teacher");
        User savedUser = userRepository.save(newUser);

        teacher.setUser(savedUser);

        return teacherRepository.save(teacher);
    }

    public void deleteByTeacherIdNumber(int idNumber) throws ResourceNotFoundException {
        Teacher teacher = teacherRepository.findByTeacherIdNumber(idNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found with ID number: " + idNumber));
        teacherRepository.delete(teacher);
    }
    @Override
    public Teacher update(int idNumber, Teacher teacher) throws ResourceNotFoundException {
        Teacher existingTeacher = findByTeacherIdNumber(idNumber);

        if (teacher.getUser() != null) {
            User existingUser = existingTeacher.getUser();
            User userToUpdate = teacher.getUser();

            if (userToUpdate.getIdNumber() > 0) {
                existingUser.setIdNumber(userToUpdate.getIdNumber());
            }
            if (userToUpdate.getName() != null) {
                existingUser.setName(userToUpdate.getName());
            }
            if (userToUpdate.getLastname() != null) {
                existingUser.setLastname(userToUpdate.getLastname());
            }
            if (userToUpdate.getUserName() != null) {
                existingUser.setUserName(userToUpdate.getUserName());
            }
            if (userToUpdate.getEmail() != null) {
                existingUser.setEmail(userToUpdate.getEmail());
            }
            if (userToUpdate.getPassword() != null) {
                existingUser.setPassword(userToUpdate.getPassword());
            }

            existingTeacher.setUser(existingUser);
        }

        if (teacher.getSpecialization() != null) {
            existingTeacher.setSpecialization(teacher.getSpecialization());
        }

        return teacherRepository.save(existingTeacher);
    }


    public Teacher findByUserId(Long userId) {
        return teacherRepository.findByUserId(userId);
    }

    public Grade getGradesForStudent(List<GroupCourse> groupCourseAux, int studentId) throws ResourceNotFoundException {
        Student student = studentService.findByIdNumber(studentId);

        for (Grade grade:gradeService.findAll()){
            for (GroupCourse groupCourse: groupCourseAux){
                if (grade.getStudent().getUser().getIdNumber() == student.getUser().getIdNumber() && grade.getGroup_has_course().getId().equals(groupCourse.getId())) {
                    return grade;
                }
            }

        }
        throw new ResourceNotFoundException("Grade information is missing.");

    }




}
