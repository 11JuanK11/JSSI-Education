package project.jssi_education.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jssi_education.entity.Attendance;
import project.jssi_education.exception.ResourceNotFoundException;
import project.jssi_education.repository.IAttendanceRepository;
import project.jssi_education.service.IAttendanceService;

import java.util.List;

@Service
public class AttendanceService implements IAttendanceService {

    @Autowired
    private IAttendanceRepository attendanceRepository;

    @Override
    public List<Attendance> findAll() {
        return attendanceRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        if (!attendanceRepository.existsById(id)) {
            throw new ResourceNotFoundException("Offer not found with id: " + id);
        }
        attendanceRepository.deleteById(id);
    }
}
