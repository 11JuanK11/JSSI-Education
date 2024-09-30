package project.jssi_education.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jssi_education.entity.TeacherEvaluation;
import project.jssi_education.exception.ResourceNotFoundException;
import project.jssi_education.repository.ITeacherEvaluationRepository;
import project.jssi_education.service.ITeacherEvaluationService;

import java.util.List;

@Service
public class TeacherEvaluationService implements ITeacherEvaluationService {

    @Autowired
    private ITeacherEvaluationRepository teacherEvaluationRepository;

    @Override
    public List<TeacherEvaluation> findAll() {
        return teacherEvaluationRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        if (!teacherEvaluationRepository.existsById(id)) {
            throw new ResourceNotFoundException("Offer not found with id: " + id);
        }
        teacherEvaluationRepository.deleteById(id);
    }
}
