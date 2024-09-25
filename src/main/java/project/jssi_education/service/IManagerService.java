package project.jssi_education.service;

import project.jssi_education.entity.Manager;
import project.jssi_education.exception.ResourceNotFoundException;

import java.util.List;

public interface IManagerService {
    public Manager findById(Long id) throws ResourceNotFoundException;
    public Manager findByIdNumber(Long id) throws ResourceNotFoundException;
    public List<Manager> findAll();
    public Manager insert(Manager manager) throws ResourceNotFoundException;
    public void deleteById(Long id) throws ResourceNotFoundException;
    public void deleteByIdNumber(Long id) throws ResourceNotFoundException;
    public void update(Long id, Manager manager) throws ResourceNotFoundException;
    public void updateByIdNumber(Long id, Manager manager) throws ResourceNotFoundException;
}
