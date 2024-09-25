package project.jssi_education.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jssi_education.entity.Grade;
import project.jssi_education.exception.ResourceNotFoundException;
import project.jssi_education.repository.IGradeRepository;
import project.jssi_education.service.IGradeService;

import java.util.List;

@Service
public class GradeService implements IGradeService {
    @Autowired
    private IGradeRepository gradeRepository;

    @Override
    public Grade findById(Long id) throws ResourceNotFoundException {
        return gradeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Grade not found with id: " + id));

    }

    @Override
    public List<Grade> findAll() {
        return gradeRepository.findAll();
    }

    @Override
    public void insert(Grade grade) throws ResourceNotFoundException {
        if(grade.getGroup_has_course().getId() == null || grade.getStudent().getId() == null){//get user get idnumber
            throw new ResourceNotFoundException("Grade information is missing.");
        }
        gradeRepository.save(grade);
    }

    @Override
    public void deleteById(Long id) throws ResourceNotFoundException {
        if (!gradeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Grade not found with id: " + id);
        }
        gradeRepository.deleteById(id);
    }

    @Override
    public void update(Long id, Grade grade) throws ResourceNotFoundException {
        Grade existingGrade = findById(id);
        if (grade.getStudent().getId() != null && grade.getGroup_has_course().getId() != null){
            if (grade.getTestOne() != null){
                existingGrade.setTestOne(grade.getTestOne());
            }
            if (grade.getTestTwo() != null){
                existingGrade.setTestTwo(grade.getTestTwo());
            }
            if (grade.getFollowUp() != null){
                existingGrade.setFollowUp(grade.getFollowUp());
            }
        }else{
            throw new ResourceNotFoundException("Grade information is missing");
        }
        gradeRepository.save(existingGrade);
    }
}
