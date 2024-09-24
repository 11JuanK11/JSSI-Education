package project.jssi_education.service;

import project.jssi_education.entity.Semester;

import java.util.List;
import java.util.Optional;

public interface ISemesterService {
    Semester createSemester(Semester semester);
    List<Semester> getAllSemesters();
    Optional<Semester> getSemesterById(Long id);
    Semester updateSemester(Long id, Semester semesterDetails);
    void deleteSemester(Long id);
}
