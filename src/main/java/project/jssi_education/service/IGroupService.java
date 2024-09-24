package project.jssi_education.service;

import project.jssi_education.entity.Group;
import project.jssi_education.exception.ResourceNotFoundException;

import java.util.List;

public interface IGroupService {
    public Group findById(Long id) throws ResourceNotFoundException;
    public List<Group> findAll();
    public void insert(Group group) throws ResourceNotFoundException;
    public void deleteById(Long id) throws ResourceNotFoundException;
    public void update(Long id, Group group) throws ResourceNotFoundException;
}
