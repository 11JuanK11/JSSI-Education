package project.jssi_education.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jssi_education.entity.Offer;
import project.jssi_education.exception.ResourceNotFoundException;

import project.jssi_education.repository.IOfferRepository;
import project.jssi_education.service.IOfferService;

import java.util.List;

@Service
public class OfferService implements IOfferService {
    @Autowired
    private IOfferRepository offerRepository;

    @Override
    public Offer FindById(Long id) throws ResourceNotFoundException {
        return offerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Offer not found with id: " + id));

    }

    @Override
    public List<Offer> FindAll() {
        return offerRepository.findAll();
    }

    @Override
    public void Insert(Offer offer) throws ResourceNotFoundException {
        if (offer.getEndTime() == null || offer.getStarTime() == null) {
            throw new ResourceNotFoundException("Offer information is missing.");
        }
        offerRepository.save(offer);
    }

    @Override
    public void deleteById(Long id) throws ResourceNotFoundException {
        if (!offerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Offer not found with id: " + id);
        }
        offerRepository.deleteById(id);
    }

    @Override
    public void Update(Long id, Offer offer) throws ResourceNotFoundException {
        Offer existingOffer = FindById(id);
        if(offer.getStarTime() != null || offer.getEndTime() != null) {
            if (offer.getStarTime() != null) {
                existingOffer.setStarTime(offer.getStarTime());
            }
            if (offer.getEndTime() != null) {
                existingOffer.setEndTime(offer.getEndTime());
            }
        }else{
            throw new ResourceNotFoundException("Offer information is missing");
        }
        offerRepository.save(existingOffer);


    }
}
