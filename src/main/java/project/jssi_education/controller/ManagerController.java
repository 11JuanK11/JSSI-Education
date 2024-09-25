package project.jssi_education.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.jssi_education.entity.Manager;
import project.jssi_education.entity.User;
import project.jssi_education.exception.ResourceNotFoundException;
import project.jssi_education.service.IManagerService;
import project.jssi_education.service.impl.UserService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/managers")
public class ManagerController {
    @Autowired
    private IManagerService managerService;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public List<Manager> findAll(){
        return managerService.findAll();
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<?> findOne(@PathVariable Long id) {
//        try {
//            Manager manager = managerService.findById(id);
//            return new ResponseEntity<>(manager, HttpStatus.OK);
//        } catch (ResourceNotFoundException ex) {
//            return new ResponseEntity<>(Map.of("message", ex.getMessage()), HttpStatus.NOT_FOUND);
//        } catch (Exception ex) {
//            return new ResponseEntity<>(Map.of("message", "Internal server error"), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @GetMapping("/{idNumber}")
    public ResponseEntity<?> findIdNumber(@PathVariable int idNumber) {
        try {
            Manager manager = managerService.findByIdNumber(idNumber);
            return new ResponseEntity<>(manager, HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(Map.of("message", ex.getMessage()), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(Map.of("message", "Internal server error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> insert(@RequestBody Manager manager) {
        try {

            Manager newManager = managerService.insert(manager);
            return new ResponseEntity<>(newManager, HttpStatus.CREATED);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>("An error occurred while creating the manager.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> delete(@PathVariable Long id) {
//        try {
//            managerService.deleteById(id);
//            return new ResponseEntity<>(Map.of("message", "Manager successfully deleted."), HttpStatus.NO_CONTENT);
//        } catch (ResourceNotFoundException ex) {
//            return new ResponseEntity<>(Map.of("message", ex.getMessage()), HttpStatus.NOT_FOUND);
//        } catch (Exception ex) {
//            return new ResponseEntity<>(Map.of("message", "Internal server error"), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @DeleteMapping("/{idNumber}")
    public ResponseEntity<?> deleteByIdNumber(@PathVariable int idNumber) {
        try {
            managerService.deleteByIdNumber(idNumber);
            return new ResponseEntity<>(Map.of("message", "Manager successfully deleted."), HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(Map.of("message", ex.getMessage()), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(Map.of("message", "Internal server error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody Manager manager) {
//        try {
//            managerService.update(id, manager);
//            return new ResponseEntity<>("Manager successfully updated.", HttpStatus.OK);
//        } catch (ResourceNotFoundException ex) {
//            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
//        } catch (Exception ex) {
//            return new ResponseEntity<>("An error occurred while updating the student.", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @PutMapping("/{idNumber}")
    public ResponseEntity<?> updateByIdNumber(@PathVariable int idNumber, @RequestBody Manager manager) {
        try {
            Manager updatedManager = managerService.updateByIdNumber(idNumber, manager);
            return new ResponseEntity<>(updatedManager, HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>("An error occurred while updating the student.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
