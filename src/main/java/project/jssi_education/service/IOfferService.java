package project.jssi_education.service;

import project.jssi_education.entity.Offer;
import project.jssi_education.entity.OfferDayWeek;
import project.jssi_education.exception.ResourceNotFoundException;

import java.util.List;

public interface IOfferService {
    public Offer findById(Long id) throws ResourceNotFoundException;
    public List<Offer> findAll();
    public void insert(Offer offer) throws ResourceNotFoundException;
    public void deleteById(Long id) throws ResourceNotFoundException;
    public void update(Long id, Offer offer) throws ResourceNotFoundException;

}
