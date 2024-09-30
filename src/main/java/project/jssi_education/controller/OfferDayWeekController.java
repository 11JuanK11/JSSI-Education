package project.jssi_education.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.jssi_education.entity.Offer;
import project.jssi_education.entity.OfferDayWeek;
import project.jssi_education.exception.ResourceNotFoundException;
import project.jssi_education.service.impl.OfferDayWeekService;
import project.jssi_education.service.impl.OfferService;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/offers/dayWeek")
public class OfferDayWeekController {
    @Autowired
    private OfferService offerService;

    @Autowired
    OfferDayWeekService offerDayWeekService;

    @GetMapping("/")
    public List<OfferDayWeek> findAll(){
        return offerDayWeekService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findOne(@PathVariable Long id) {
        try {
            OfferDayWeek offerDayWeek = offerDayWeekService.findById(id);

            return new ResponseEntity<>(offerDayWeek, HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(Map.of("message", ex.getMessage()), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(Map.of("message", "Internal server error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> Insert(@RequestBody OfferDayWeek offerDayWeek) {
        try {
            Offer offer = offerService.insert(offerDayWeek.getOffer());
            offerDayWeek.setOffer(offer);
            OfferDayWeek offerDayWeekSave = offerDayWeekService.insert(offerDayWeek);
            return new ResponseEntity<>(offerDayWeekSave, HttpStatus.CREATED);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>("An error occurred while creating the Offer Day Week.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteByIdNumber(@PathVariable Long id) {
        try {
            offerDayWeekService.deleteById(id);
            return new ResponseEntity<>(Map.of("message", "Offer Day Week successfully deleted."), HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(Map.of("message", ex.getMessage()), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(Map.of("message", "Internal server error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody OfferDayWeek offerDayWeek) {
        try {
            OfferDayWeek offerDayWeekSave = offerDayWeekService.update(id, offerDayWeek);
            return new ResponseEntity<>(offerDayWeekSave, HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>("An error occurred while updating the Offer Day Week.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deletes/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            offerDayWeekService.deleteDidacticMaterial(id);
            offerDayWeekService.deleteAttendance(id);
            offerDayWeekService.deleteTeacherEvaluation(id);
            offerDayWeekService.deleteGrade(id);
            offerDayWeekService.deleteGroupCourse(id);
            offerDayWeekService.deleteGroup(id);
            offerDayWeekService.deleteById(id);
            return new ResponseEntity<>(Map.of("message", "Offer Day Week successfully deleted."), HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(Map.of("message", ex.getMessage()), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(Map.of("message", "Internal server error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
