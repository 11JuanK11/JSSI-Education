package project.jssi_education.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.jssi_education.entity.Offer;
import project.jssi_education.exception.ResourceNotFoundException;
import project.jssi_education.repository.IOfferRepository;
import project.jssi_education.service.IOfferService;

@Service
public class OfferService implements IOfferService {
    @Autowired
    private IOfferRepository offerRepository;

    @Override
    public Offer findById(Long id) throws ResourceNotFoundException {
        return offerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Offer not found with id: " + id));

    }

    @Override
    public List<Offer> findAll() {
        return offerRepository.findAll();
    }

    @Override
    public void insert(Offer offer) throws ResourceNotFoundException {
        if (offer.getEndTime() == null || offer.getStartTime() == null) {
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
    public void update(Long id, Offer offer) throws ResourceNotFoundException {
        Offer existingOffer = findById(id);
        if(offer.getStartTime() != null || offer.getEndTime() != null) {
            if (offer.getStartTime() != null) {
                existingOffer.setStartTime(offer.getStartTime());
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
