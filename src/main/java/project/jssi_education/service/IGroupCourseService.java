package project.jssi_education.service;

import project.jssi_education.entity.Group;
import project.jssi_education.entity.GroupCourse;
import project.jssi_education.exception.ResourceNotFoundException;

import java.util.List;

public interface IGroupCourseService {
    public GroupCourse findById(Long id) throws ResourceNotFoundException;
    public List<GroupCourse> findAll();
    public void insert(GroupCourse groupCourse) throws ResourceNotFoundException;
    public void deleteById(Long id) throws ResourceNotFoundException;
    public void update(Long id, GroupCourse groupCourse) throws ResourceNotFoundException;

}
