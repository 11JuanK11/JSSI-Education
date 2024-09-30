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
    public Attendance findById(Long id) throws ResourceNotFoundException {
        return attendanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Attendance not found with id: " + id));
    }

    @Override
    public void deleteById(Long id) {
        if (!attendanceRepository.existsById(id)) {
            throw new ResourceNotFoundException("Attendance not found with id: " + id);
        }
        attendanceRepository.deleteById(id);
    }

    @Override
    public Attendance insert(Attendance attendance) throws ResourceNotFoundException {
        if(attendance.getGroupHasCourse().getId() == null || attendance.getStudent().getId() == null){//get user get idnumber
            throw new ResourceNotFoundException("Attendance information is missing.");
        }
        return attendanceRepository.save(attendance);
    }

    @Override
    public Attendance update(Long id, Attendance attendance) throws ResourceNotFoundException {
        Attendance attendance1 = findById(id);
        if (attendance.getStudent().getId() != null && attendance.getGroupHasCourse().getId() != null){
            if (attendance.getDate() != null){
                attendance1.setDate(attendance.getDate());
            }
            if (attendance.getStatus() != null){
                attendance1.setStatus(attendance.getStatus());
            }
        }else{
            throw new ResourceNotFoundException("Grade information is missing");
        }
        return attendanceRepository.save(attendance1);
    }

    @Override
    public List<Attendance> findByStudentId(Long studentId) {
        return attendanceRepository.findByStudentId(studentId);
    }
}
