package project.jssi_education.service;

import project.jssi_education.entity.Attendance;
import project.jssi_education.entity.Grade;
import project.jssi_education.entity.TeacherEvaluation;
import project.jssi_education.exception.ResourceNotFoundException;

import java.util.List;

public interface IAttendanceService {
    public List<Attendance> findAll();
    public Attendance findById(Long id) throws ResourceNotFoundException;
    public void deleteById(Long id);
    public Attendance insert(Attendance attendance) throws ResourceNotFoundException;
    public Attendance update(Long id, Attendance attendance) throws ResourceNotFoundException;
    List<Attendance> findByStudentId(Long studentId);
}
