package project.jssi_education.entity;

import java.util.Date;

import jakarta.persistence.Entity;

@Entity
public class Attendance {
    
    private int attendanceId;
    private Date date;
    private boolean status;
}
