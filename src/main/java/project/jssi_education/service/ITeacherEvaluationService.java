package project.jssi_education.service;

import project.jssi_education.entity.TeacherEvaluation;

import java.util.List;

public interface ITeacherEvaluationService {
    public List<TeacherEvaluation> findAll();
    public void deleteById(Long id);
}
