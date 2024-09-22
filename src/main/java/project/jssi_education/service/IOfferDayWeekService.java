package project.jssi_education.service;

import project.jssi_education.entity.OfferDayWeek;
import project.jssi_education.exception.ResourceNotFoundException;

import java.util.List;

public interface IOfferDayWeekService {
    public OfferDayWeek FindById(Long id) throws ResourceNotFoundException;
    public List<OfferDayWeek> FindAll();
    public void Insert(OfferDayWeek offerDayWeek) throws ResourceNotFoundException;
    public void deleteById(Long id) throws ResourceNotFoundException;
    public void Update(Long id, OfferDayWeek offerDayWeek) throws ResourceNotFoundException;

}
