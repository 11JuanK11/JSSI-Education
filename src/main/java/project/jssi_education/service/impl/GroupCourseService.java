package project.jssi_education.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import project.jssi_education.entity.GroupCourse;
import project.jssi_education.exception.ResourceNotFoundException;
import project.jssi_education.repository.ICourseRepository;

import project.jssi_education.repository.IGroupCourseRepository;
import project.jssi_education.repository.IGroupRepository;
import project.jssi_education.service.IGroupCourseService;

import java.util.List;
@Service
public class GroupCourseService implements IGroupCourseService {
    @Autowired
    private IGroupCourseRepository groupCourseRepository;
    @Autowired
    private ICourseRepository courseRepository;
    @Autowired
    private IGroupRepository groupRepository;

    @Override
    public GroupCourse findById(Long id) throws ResourceNotFoundException {
        return groupCourseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Group Course not found with id: " + id));
    }

    @Override
    public List<GroupCourse> findAll() {
        return groupCourseRepository.findAll();
    }

    @Override
    public GroupCourse insert(GroupCourse groupCourse) throws ResourceNotFoundException {
        if(groupCourse.getCourse() == null || groupCourse.getGroup() == null){
            throw new ResourceNotFoundException("Group Course information is missing.");
        }
        return groupCourseRepository.save(groupCourse);
    }

    @Override
    public void deleteById(Long id) throws ResourceNotFoundException {
        if (!groupCourseRepository.existsById(id)) {
            throw new ResourceNotFoundException("Group Course not found with id: " + id);
        }
        groupCourseRepository.deleteById(id);

    }

    @Override
    public GroupCourse update(Long id, GroupCourse groupCourse) throws ResourceNotFoundException {
        GroupCourse existingGroupCourse = findById(id);
        if (groupCourse.getCourse() != null || groupCourse.getGroup() != null || groupCourse.getAttendances() != null || groupCourse.getGrades() != null || groupCourse.getTeacher_evaluations() != null){
            if(groupCourse.getCourse() != null){
                existingGroupCourse.setCourse(courseRepository.getReferenceById(groupCourse.getCourse().getCourseId()));
            }
            if (groupCourse.getGroup() != null){
                existingGroupCourse.setGroup(groupRepository.getReferenceById(groupCourse.getGroup().getGroupId()));
            }
            if(groupCourse.getAttendances() != null){
                existingGroupCourse.setAttendances(groupCourse.getAttendances());
            }
            if(groupCourse.getGrades() != null){
                existingGroupCourse.setGrades(groupCourse.getGrades());
            }
            if(groupCourse.getTeacher_evaluations()!= null){
                existingGroupCourse.setTeacher_evaluations(groupCourse.getTeacher_evaluations());
            }
        }else{
            throw new ResourceNotFoundException("Group Course information is missing");
        }
        return groupCourseRepository.save(groupCourse);
    }
}
