package project.jssi_education.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jssi_education.entity.Degree;
import project.jssi_education.exception.ResourceNotFoundException;
import project.jssi_education.repository.IDegreeRepository;
import project.jssi_education.service.IDegreeService;

import java.util.List;

@Service
public class DegreeService implements IDegreeService {
    @Autowired
    private IDegreeRepository repo;
    @Override
    public Degree findById(Long id) throws ResourceNotFoundException{
        for (Degree degree : repo.findAll()) {
            if (degree.getId().equals(id)) {
                return degree;
            }
        }
        throw new ResourceNotFoundException("Degree with id " + id + " not found.");
    }
    @Override
    public List<Degree> findAll(){
        return repo.findAll();
    }
    @Override
    public void insert(Degree degree){
        repo.save(degree);
    }
}
