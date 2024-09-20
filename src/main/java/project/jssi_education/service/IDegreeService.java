package project.jssi_education.service;

import project.jssi_education.entity.Degree;

import java.util.List;

public interface IDegreeService {
    public Degree FindbyId(Long id);
    public List<Degree> FindAll();
    public void Insert(Degree degree);
}
