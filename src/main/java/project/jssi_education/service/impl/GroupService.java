package project.jssi_education.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jssi_education.entity.Group;
import project.jssi_education.exception.ResourceNotFoundException;
import project.jssi_education.repository.IGroupRepository;
import project.jssi_education.service.IGroupService;

import java.util.List;

@Service
public class GroupService implements IGroupService {
    @Autowired
    private IGroupRepository groupRepository;

    @Autowired
    private OfferService offerService;

    @Autowired
    private  TeacherService teacherService;

    @Override
    public Group findById(Long id) throws ResourceNotFoundException {
        return groupRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Group not found with id: " + id));
    }

    @Override
    public List<Group> findAll() {
        return groupRepository.findAll();
    }

    @Override
    public void insert(Group group) throws ResourceNotFoundException {
        if (group.getOffer() == null && group.getTeacher() == null && group.getClassroom() == null && group.getGroup_has_course() == null && group.getNumberStudents() == null) {
            throw new ResourceNotFoundException("group information is missing.");
        }
        groupRepository.save(group);
    }

    @Override
    public void deleteById(Long id) throws ResourceNotFoundException {
        if (!groupRepository.existsById(id)) {
            throw new ResourceNotFoundException("Offer not found with id: " + id);
        }
        groupRepository.deleteById(id);
    }

    @Override
    public void update(Long id, Group group) throws ResourceNotFoundException {
        Group existingGroup = findById(id);
        if(group.getOffer() != null || group.getTeacher() != null || group.getClassroom() != null || group.getGroup_has_course() != null || group.getNumberStudents() != null) {
            if (group.getOffer() != null) {
                existingGroup.setOffer(offerService.findById(group.getOffer().getId()));
            }
            if (group.getTeacher() != null) {
                existingGroup.setTeacher(teacherService.findByTeacherIdNumber(group.getTeacher().getUser().getIdNumber()));
            }
            if (group.getClassroom() != null){
                existingGroup.setClassroom(group.getClassroom());
            }
            if(group.getGroup_has_course() != null){
                existingGroup.setGroup_has_course(group.getGroup_has_course());
            }
            if( group.getNumberStudents() != null){
                existingGroup.setNumberStudents(group.getNumberStudents());
            }
        }else{
            throw new ResourceNotFoundException("Group information is missing");
        }
        groupRepository.save(existingGroup);
    }
}
