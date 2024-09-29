package project.jssi_education.service;

import project.jssi_education.entity.OfferDayWeek;
import project.jssi_education.exception.ResourceNotFoundException;

import java.util.List;

public interface IOfferDayWeekService {
    public OfferDayWeek findById(Long id) throws ResourceNotFoundException;
    public List<OfferDayWeek> findAll();
    public OfferDayWeek insert(OfferDayWeek offerDayWeek) throws ResourceNotFoundException;
    public void deleteById(Long id) throws ResourceNotFoundException;
    public OfferDayWeek update(Long id, OfferDayWeek offerDayWeek) throws ResourceNotFoundException;

}
