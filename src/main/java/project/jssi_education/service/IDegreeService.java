package project.jssi_education.service;

import project.jssi_education.entity.Degree;
import project.jssi_education.exception.ResourceNotFoundException;

import java.util.List;

public interface IDegreeService {
    public Degree findById(Long id) throws ResourceNotFoundException;
    public List<Degree> findAll();
    public void insert(Degree degree);
}
