package project.jssi_education.service;

import project.jssi_education.entity.GroupCourse;
import project.jssi_education.exception.ResourceNotFoundException;

import java.util.List;

public interface IGroupCourseService {
    public GroupCourse findById(Long id) throws ResourceNotFoundException;
    public List<GroupCourse> findAll();
    public GroupCourse insert(GroupCourse groupCourse) throws ResourceNotFoundException;
    public void deleteById(Long id) throws ResourceNotFoundException;
    public GroupCourse update(Long id, GroupCourse groupCourse) throws ResourceNotFoundException;

}
