package project.jssi_education.service;

import project.jssi_education.entity.Grade;
import project.jssi_education.exception.ResourceNotFoundException;

import java.util.List;

public interface IGradeService {
    public Grade findById(Long id) throws ResourceNotFoundException;
    public List<Grade> findAll();
    public void insert(Grade grade) throws ResourceNotFoundException;
    public void deleteById(Long id) throws ResourceNotFoundException;
    public void update(Long id, Grade grade) throws ResourceNotFoundException;
}
