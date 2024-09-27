package project.jssi_education.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jssi_education.entity.DidacticMaterial;
import project.jssi_education.exception.ResourceNotFoundException;
import project.jssi_education.repository.IDidacticMaterialRepository;
import project.jssi_education.service.IDidacticMaterialService;

import java.util.List;

@Service
public class DidacticMaterialService implements IDidacticMaterialService {

    @Autowired
    private IDidacticMaterialRepository didacticMaterialsRepository;
    @Override
    public DidacticMaterial findById(Long id) throws ResourceNotFoundException {
        return didacticMaterialsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Didactic Material not found with id: " + id));

    }

    @Override
    public List<DidacticMaterial> findAll() {
        return didacticMaterialsRepository.findAll();
    }

    @Override
    public DidacticMaterial insert(DidacticMaterial didacticMaterial) throws ResourceNotFoundException {
        if (didacticMaterial.getDescription() != null && didacticMaterial.getId() != null ) {
            throw new ResourceNotFoundException("Didactic Material information is missing.");
        }
        return didacticMaterialsRepository.save(didacticMaterial);
    }

    @Override
    public void deleteById(Long id) throws ResourceNotFoundException {
        if (!didacticMaterialsRepository.existsById(id)) {
            throw new ResourceNotFoundException("Didactic Materials not found with id: " + id);
        }
        didacticMaterialsRepository.deleteById(id);

    }

    @Override
    public void update(Long id, DidacticMaterial didacticMaterial) throws ResourceNotFoundException {
        DidacticMaterial existingDidacticMaterial = findById(id);
        if (didacticMaterial.getDescription() != null || didacticMaterial.getId() != null ) {
            if (didacticMaterial.getDescription()  != null) {
                existingDidacticMaterial.setDescription(didacticMaterial.getDescription());
            }
        }else{
            throw new ResourceNotFoundException("Didactic Material information is missing");
        }
        didacticMaterialsRepository.save(existingDidacticMaterial);


    }
}
