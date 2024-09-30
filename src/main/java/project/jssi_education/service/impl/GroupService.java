package project.jssi_education.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jssi_education.entity.Course;
import project.jssi_education.entity.Group;
import project.jssi_education.entity.GroupCourse;
import project.jssi_education.entity.Teacher;
import project.jssi_education.exception.ResourceNotFoundException;
import project.jssi_education.repository.IGroupRepository;
import project.jssi_education.service.IGroupService;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupService implements IGroupService {
    @Autowired
    private IGroupRepository groupRepository;

    @Autowired
    private OfferDayWeekService offerDayWeekService;

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
    public Group insert(Group group) throws ResourceNotFoundException {
        if (group.getClassroom() == null && group.getNumberStudents() == null) {
            throw new ResourceNotFoundException("group information is missing.");
        }
        return groupRepository.save(group);
    }

    @Override
    public void deleteById(Long id) throws ResourceNotFoundException {
        if (!groupRepository.existsById(id)) {
            throw new ResourceNotFoundException("Offer not found with id: " + id);
        }
        groupRepository.deleteById(id);
    }

    @Override
    public Group update(Long id, Group group) throws ResourceNotFoundException {
        Group existingGroup = findById(id);
        if(group.getOfferDayWeek() != null || group.getTeacher() != null || group.getClassroom() != null || group.getGroupHasCourse() != null || group.getNumberStudents() != null) {
            if (group.getOfferDayWeek() != null && group.getOfferDayWeek().getId() != null) {
                existingGroup.setOfferDayWeek(offerDayWeekService.findById(group.getOfferDayWeek().getId()));
            }
            if (group.getTeacher() != null && group.getTeacher().getId() != null) {
                existingGroup.setTeacher(teacherService.findByTeacherIdNumber(group.getTeacher().getUser().getIdNumber()));
            }
            if (group.getClassroom() != null){
                existingGroup.setClassroom(group.getClassroom());
            }
            if(group.getGroupHasCourse() != null){
                existingGroup.setGroupHasCourse(group.getGroupHasCourse());
            }
            if( group.getNumberStudents() != null){
                existingGroup.setNumberStudents(group.getNumberStudents());
            }
        }else{
            throw new ResourceNotFoundException("Group information is missing");
        }
        return groupRepository.save(existingGroup);
    }

    @Override
    public List<Group> findByTeacher(Teacher teacher) {
        return groupRepository.findByTeacher(teacher);
    }

    @Override
    public List<Course> findCoursesByGroup(Group group) {
        return groupRepository.findCoursesByGroupId(group.getGroupId());
    }

    public List<Group> getGroupsByTeacher(int teacherId) throws ResourceNotFoundException {
        Teacher teacher = teacherService.findByTeacherIdNumber(teacherId);
        List<Group> groupsAux = new ArrayList<>();

        for(Group groups: groupRepository.findAll()){
            if(groups.getTeacher().getUser().getIdNumber() == teacher.getUser().getIdNumber()){
                groupsAux.add(groups);
            }
        }
        return groupsAux;
    }

    public List<Group> getGroupsbyGroupCourse(List<GroupCourse> studentGroupCourses) throws ResourceNotFoundException {
        List<Group> groupsAux = new ArrayList<>();

        for(Group group: groupRepository.findAll()){
            for (GroupCourse studentGroupCourse: studentGroupCourses){
                if (group.getGroupId().equals(studentGroupCourse.getGroup().getGroupId())){
                    groupsAux.add(group);
                }
            }

        }
        return groupsAux;
    }

}
