package project.jssi_education.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jssi_education.entity.*;

import project.jssi_education.exception.ResourceNotFoundException;
import project.jssi_education.repository.IDayWeekRepository;
import project.jssi_education.repository.IOfferDayWeekRepository;
import project.jssi_education.repository.IOfferRepository;
import project.jssi_education.service.IOfferDayWeekService;

import javax.swing.*;
import java.util.List;

@Service
public class OfferDayWeekService implements IOfferDayWeekService {
    @Autowired
    private IOfferDayWeekRepository offerDayWeekRepository;

    @Autowired
    private IDayWeekRepository dayWeekRepository;

    @Autowired
    private OfferService offerService;


    @Override
    public OfferDayWeek FindById(Long id) throws ResourceNotFoundException {
        return offerDayWeekRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Offer Day Week not found with id: " + id));
    }

    @Override
    public List<OfferDayWeek> FindAll() {
        return offerDayWeekRepository.findAll();
    }

    @Override
    public void Insert(OfferDayWeek offerDayWeek) throws ResourceNotFoundException {
        if (offerDayWeek.getOffer() == null) {
            throw new ResourceNotFoundException("Offer information is missing.");
        }
        Offer offer = offerService.FindById(offerDayWeek.getOffer().getId());

        long day_id = offerDayWeek.getDayWeek().getId();
        if (day_id < 1 || day_id > 7)
            throw new ResourceNotFoundException("Day Week not found with id: " + offerDayWeek.getDayWeek().getId());

        offerDayWeekRepository.save(offerDayWeek);
    }

    @Override
    public void deleteById(Long id) throws ResourceNotFoundException {
        if (!offerDayWeekRepository.existsById(id)) {
            throw new ResourceNotFoundException("Offer not found with id: " + id);
        }
        offerDayWeekRepository.deleteById(id);
    }

    @Override
    public void Update(Long id, OfferDayWeek offerDayWeek) throws ResourceNotFoundException {
        OfferDayWeek existingOfferDayWeek = FindById(id);
        if (offerDayWeek.getOffer() != null && offerDayWeek.getOffer().getId() != null) {

            Offer offerToUpdate = offerService.FindById(offerDayWeek.getOffer().getId());

            existingOfferDayWeek.setOffer(offerToUpdate);
        }
        if (offerDayWeek.getDayWeek() != null && offerDayWeek.getDayWeek().getId() != null) {
            long dayWeekId = offerDayWeek.getDayWeek().getId();

            if (dayWeekId >= 1  && dayWeekId <= 7){
                DayWeek dayWeekToUpdate = dayWeekRepository.getReferenceById(dayWeekId);
                existingOfferDayWeek.setDayWeek(dayWeekToUpdate);
            }
        }
        offerDayWeekRepository.save(existingOfferDayWeek);
    }
}

