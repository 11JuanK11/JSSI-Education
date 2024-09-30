package project.jssi_education.service;

import project.jssi_education.entity.Semester;

import java.util.List;
import java.util.Optional;

public interface ISemesterService {
    Semester insert(Semester semester);
    List<Semester> findAll();
    Optional<Semester> findById(Long id);
    Semester update(Long id, Semester semesterDetails);
    void delete(Long id);
}
