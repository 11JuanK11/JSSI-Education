package project.jssi_education.entity;

import java.util.ArrayList;

import jakarta.persistence.Entity;

@Entity
public class Grade {
    
    private int gradeId;
    private ArrayList<Float> score;
    private boolean status;
}
