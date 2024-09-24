package project.jssi_education.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jssi_education.entity.Manager;
import project.jssi_education.entity.User;
import project.jssi_education.exception.ResourceNotFoundException;
import project.jssi_education.repository.IManagerRepository;
import project.jssi_education.repository.IUserRepository;
import project.jssi_education.service.IManagerService;

import java.util.List;

@Service
public class ManagerService implements IManagerService {

    @Autowired
    private IManagerRepository managerRepository;

    @Autowired
    private IUserRepository userRepository;

    @Override
    public Manager findById(Long id) throws ResourceNotFoundException {
        return managerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Manager not found with id: " + id));
    }

    @Override
    public Manager findByIdNumber(Long idNumber) throws ResourceNotFoundException {
        return managerRepository.findById(idNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Manager not found with id: " + idNumber));
    }

    @Override
    public List<Manager> findAll() {
        return managerRepository.findAll();
    }

    @Override
    public void insert(Manager manager) throws ResourceNotFoundException {
        if (manager.getUser() == null || manager.getUser().getId() == null) {
            throw new ResourceNotFoundException("User information is missing.");
        }
        User user = userRepository.findById(manager.getUser().getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + manager.getUser().getId()));

        manager.setUser(user);
        managerRepository.save(manager);
    }

    @Override
    public void deleteById(Long id) throws ResourceNotFoundException {
        managerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        managerRepository.deleteById(id);
    }

    @Override
    public void deleteByIdNumber(Long idNumber) throws ResourceNotFoundException {
        managerRepository.findById(idNumber)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + idNumber));

        managerRepository.deleteById(idNumber);
    }

    @Override
    public void update(Long id, Manager manager) throws ResourceNotFoundException {
        Manager existingManager = findById(id);

        if (manager.getUser() != null) {
            User userToUpdate = existingManager.getUser();

            if (manager.getUser().getIdNumber() > 0) {
                userToUpdate.setIdNumber(manager.getUser().getIdNumber());
            }
            if (manager.getUser().getName() != null) {
                userToUpdate.setName(manager.getUser().getName());
            }
            if (manager.getUser().getLastname() != null) {
                userToUpdate.setLastname(manager.getUser().getLastname());
            }
            if (manager.getUser().getUserName() != null) {
                userToUpdate.setUserName(manager.getUser().getUserName());
            }
            if (manager.getUser().getEmail() != null) {
                userToUpdate.setEmail(manager.getUser().getEmail());
            }
            if (manager.getUser().getPassword() != null) {
                userToUpdate.setPassword(manager.getUser().getPassword());
            }
            existingManager.setUser(userToUpdate);
        }

        managerRepository.save(existingManager);
    }

    @Override
    public void updateByIdNumber(Long idNumber, Manager manager) throws ResourceNotFoundException {
        Manager existingManager = findById(idNumber);

        if (manager.getUser() != null) {
            User userToUpdate = existingManager.getUser();

            if (manager.getUser().getIdNumber() > 0) {
                userToUpdate.setIdNumber(manager.getUser().getIdNumber());
            }
            if (manager.getUser().getName() != null) {
                userToUpdate.setName(manager.getUser().getName());
            }
            if (manager.getUser().getLastname() != null) {
                userToUpdate.setLastname(manager.getUser().getLastname());
            }
            if (manager.getUser().getUserName() != null) {
                userToUpdate.setUserName(manager.getUser().getUserName());
            }
            if (manager.getUser().getEmail() != null) {
                userToUpdate.setEmail(manager.getUser().getEmail());
            }
            if (manager.getUser().getPassword() != null) {
                userToUpdate.setPassword(manager.getUser().getPassword());
            }
            existingManager.setUser(userToUpdate);
        }

        managerRepository.save(existingManager);
    }

}
