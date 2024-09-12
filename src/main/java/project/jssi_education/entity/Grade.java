package project.jssi_education.entity;

import java.util.ArrayList;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data

public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gradeId;
    
    private ArrayList<Float> score;
    private boolean status;
}
