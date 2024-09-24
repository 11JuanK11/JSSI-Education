package project.jssi_education.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jssi_education.entity.Semester;
import project.jssi_education.repository.ISemesterRepository;
import project.jssi_education.service.ISemesterService;

import java.util.List;
import java.util.Optional;

@Service
public class SemesterService implements ISemesterService {
    @Autowired
    private ISemesterRepository semesterRepository;

    @Override
    public Semester createSemester(Semester semester) {
        return semesterRepository.save(semester);
    }

    @Override
    public List<Semester> getAllSemesters() {
        return semesterRepository.findAll();
    }

    @Override
    public Optional<Semester> getSemesterById(Long id) {
        return semesterRepository.findById(id);
    }

    @Override
    public Semester updateSemester(Long id, Semester semesterDetails) {
        Semester semester = semesterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Semester not found with id " + id));
        semester.setSemester(semesterDetails.getSemester());
        return semesterRepository.save(semester);
    }

    @Override
    public void deleteSemester(Long id) {
        Semester semester = semesterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Semester not found with id " + id));
        semesterRepository.delete(semester);
    }
}
