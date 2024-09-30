package project.jssi_education.service;

import project.jssi_education.entity.Attendance;
import project.jssi_education.entity.TeacherEvaluation;

import java.util.List;

public interface IAttendanceService {
    public List<Attendance> findAll();
    public void deleteById(Long id);
}
