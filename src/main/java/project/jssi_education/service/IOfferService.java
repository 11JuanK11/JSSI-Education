package project.jssi_education.service;

import project.jssi_education.entity.Offer;
import project.jssi_education.entity.OfferDayWeek;
import project.jssi_education.exception.ResourceNotFoundException;

import java.util.List;

public interface IOfferService {
    public Offer FindById(Long id) throws ResourceNotFoundException;
    public List<Offer> FindAll();
    public void Insert(Offer offer) throws ResourceNotFoundException;
    public void deleteById(Long id) throws ResourceNotFoundException;
    public void Update(Long id, Offer offer) throws ResourceNotFoundException;

}
