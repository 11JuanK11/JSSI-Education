package project.jssi_education.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jssi_education.entity.*;

import project.jssi_education.exception.ResourceNotFoundException;
import project.jssi_education.repository.IDayWeekRepository;
import project.jssi_education.repository.IGroupRepository;
import project.jssi_education.repository.IOfferDayWeekRepository;
import project.jssi_education.service.IOfferDayWeekService;

import java.util.List;

@Service
public class OfferDayWeekService implements IOfferDayWeekService {
    @Autowired
    private IOfferDayWeekRepository offerDayWeekRepository;

    @Autowired
    private IDayWeekRepository dayWeekRepository;

    @Autowired
    private OfferService offerService;

    @Autowired
    private GroupCourseService groupCourseService;

    @Autowired
    private IGroupRepository groupRepository;

    @Override
    public OfferDayWeek findById(Long id) throws ResourceNotFoundException {
        return offerDayWeekRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Offer Day Week not found with id: " + id));
    }

    @Override
    public List<OfferDayWeek> findAll() {
        return offerDayWeekRepository.findAll();
    }

    @Override
    public OfferDayWeek insert(OfferDayWeek offerDayWeek) throws ResourceNotFoundException {
        if (offerDayWeek.getOffer() == null) {
            throw new ResourceNotFoundException("Offer information is missing.");
        }
        Offer offer = offerService.findById(offerDayWeek.getOffer().getId());

        long day_id = offerDayWeek.getDayWeek().getId();
        if (day_id < 1 || day_id > 7)
            throw new ResourceNotFoundException("Day Week not found with id: " + offerDayWeek.getDayWeek().getId());

        return offerDayWeekRepository.save(offerDayWeek);
    }

    @Override
    public void deleteById(Long id) throws ResourceNotFoundException {
        if (!offerDayWeekRepository.existsById(id)) {
            throw new ResourceNotFoundException("Offer not found with id: " + id);
        }
        offerDayWeekRepository.deleteById(id);
    }

    @Override
    public OfferDayWeek update(Long id, OfferDayWeek offerDayWeek) throws ResourceNotFoundException {
        OfferDayWeek existingOfferDayWeek = findById(id);
        if (offerDayWeek.getOffer() != null && offerDayWeek.getOffer().getId() != null) {

            if(offerDayWeek.getOffer().getStartTime() != null) {
                existingOfferDayWeek.getOffer().setStartTime(offerDayWeek.getOffer().getStartTime());
            }
            if(offerDayWeek.getOffer().getEndTime() != null) {
                existingOfferDayWeek.getOffer().setEndTime(offerDayWeek.getOffer().getEndTime());
            }
        }
        if (offerDayWeek.getDayWeek() != null && offerDayWeek.getDayWeek().getId() != null) {
            long dayWeekId = offerDayWeek.getDayWeek().getId();

            if (dayWeekId >= 1  && dayWeekId <= 7){
                DayWeek dayWeekToUpdate = dayWeekRepository.getReferenceById(dayWeekId);
                existingOfferDayWeek.setDayWeek(dayWeekToUpdate);
            }
        }
        return offerDayWeekRepository.save(existingOfferDayWeek);
    }

    public void deleteGroupCourse(Long id){
        List<GroupCourse> groupsCourses = groupCourseService.findAll();

        for (GroupCourse groupCourse : groupsCourses){
            if(groupCourse.getGroup().getOfferDayWeek().getId().equals(id)){
                groupCourseService.deleteById(groupCourse.getId());
            }
        }
    }

    public void deleteGroup(Long id){
        List<Group> groups = groupRepository.findAll();

        for(Group group : groups){
            if(group.getOfferDayWeek().getId().equals(id)){
                groupRepository.deleteById(group.getGroupId());
            }
        }
    }

}

