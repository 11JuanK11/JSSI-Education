package project.jssi_education.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.jssi_education.entity.DidacticMaterial;
import project.jssi_education.exception.ResourceNotFoundException;
import project.jssi_education.service.impl.DidacticMaterialService;


import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/didactic-materials")
public class DidacticMaterialController {
    @Autowired
    private DidacticMaterialService didacticMaterialService;

    @GetMapping("/")
    public List<DidacticMaterial> findAll(){
        return didacticMaterialService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findOne(@PathVariable Long id) {
        try {
            DidacticMaterial didacticMaterial = didacticMaterialService.findById(id);
            return new ResponseEntity<>(didacticMaterial, HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(Map.of("message", ex.getMessage()), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(Map.of("message", "Internal server error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> insert(@RequestBody DidacticMaterial didacticMaterial) {
        try {
            DidacticMaterial createdMaterial = didacticMaterialService.insert(didacticMaterial);
            return new ResponseEntity<>(createdMaterial, HttpStatus.CREATED); // Retornar el objeto creado
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(Map.of("message", ex.getMessage()), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(Map.of("message", "An error occurred while creating the Didactic Material."), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteByIdNumber(@PathVariable Long id) {
        try {
            didacticMaterialService.deleteById(id);
            return new ResponseEntity<>(Map.of("message", "didactic Material successfully deleted."), HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(Map.of("message", ex.getMessage()), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(Map.of("message", "Internal server error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> Update(@PathVariable Long id, @RequestBody DidacticMaterial didacticMaterial) {
        try {
            didacticMaterialService.update(id, didacticMaterial);
            return new ResponseEntity<>("didactic Material successfully updated.", HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>("An error occurred while updating the didactic Material.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
