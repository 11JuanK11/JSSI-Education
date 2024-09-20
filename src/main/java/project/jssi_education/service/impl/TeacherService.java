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
        return teacherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found with id: " + id));
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

        // Crear el nuevo usuario
        User newUser = teacher.getUser();
        User savedUser = userRepository.save(newUser); // Guardamos el usuario primero

        // Asignar el usuario guardado al teacher
        teacher.setUser(savedUser);

        return teacherRepository.save(teacher); // Guardar el teacher
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

        // Actualiza el usuario si es necesario
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
}
