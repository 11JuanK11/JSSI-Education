package project.jssi_education.service;

import org.springframework.stereotype.Service;
import project.jssi_education.entity.DidacticMaterial;
import project.jssi_education.exception.ResourceNotFoundException;

import java.util.List;

public interface IDidacticMaterialService {
    public DidacticMaterial findById(Long id) throws ResourceNotFoundException;
    public List<DidacticMaterial> findAll();
    public DidacticMaterial insert(DidacticMaterial didacticMaterial) throws ResourceNotFoundException;
    public void deleteById(Long id) throws ResourceNotFoundException;
    public DidacticMaterial update(Long id, DidacticMaterial didacticMaterial) throws ResourceNotFoundException;

}
