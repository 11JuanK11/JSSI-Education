package project.jssi_education.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jssi_education.entity.DidacticMaterial;
import project.jssi_education.entity.GroupCourse;
import project.jssi_education.exception.ResourceNotFoundException;
import project.jssi_education.repository.IDidacticMaterialRepository;
import project.jssi_education.service.IDidacticMaterialService;

import java.util.List;

@Service
public class DidacticMaterialService implements IDidacticMaterialService {

    @Autowired
    private IDidacticMaterialRepository didacticMaterialsRepository;

    @Autowired
    private GroupCourseService groupCourseService;

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
        if (didacticMaterial.getDescription() == null) {
            throw new ResourceNotFoundException("Didactic Material description is missing.");
        }

        if (didacticMaterial.getGroupHasCourse() == null || didacticMaterial.getGroupHasCourse().getId() == null) {
            throw new ResourceNotFoundException("GroupCourse ID is missing.");
        }

        Long groupCourseId = didacticMaterial.getGroupHasCourse().getId();

        GroupCourse groupCourse = groupCourseService.findById(groupCourseId);

        if (groupCourse == null) {
            throw new ResourceNotFoundException("GroupCourse not found with id: " + groupCourseId);
        }

        didacticMaterial.setGroupHasCourse(groupCourse);

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
    public DidacticMaterial update(Long id, DidacticMaterial didacticMaterial) throws ResourceNotFoundException {
        DidacticMaterial existingDidacticMaterial = findById(id);
        if (didacticMaterial.getDescription() != null || didacticMaterial.getId() != null ) {
            if (didacticMaterial.getDescription()  != null) {
                existingDidacticMaterial.setDescription(didacticMaterial.getDescription());
            }
        }else{
            throw new ResourceNotFoundException("Didactic Material information is missing");
        }
        return didacticMaterialsRepository.save(existingDidacticMaterial);

    }

    public List<DidacticMaterial> findByGroupAndCourse(Long groupId, Long courseId) throws ResourceNotFoundException {
        List<DidacticMaterial> materials = didacticMaterialsRepository.findAll();
        materials.removeIf(didacticMaterial -> !didacticMaterial.getGroupHasCourse().getGroup().getGroupId().equals(groupId) || !didacticMaterial.getGroupHasCourse().getCourse().getCourseId().equals(courseId));
        return materials;
    }

}
