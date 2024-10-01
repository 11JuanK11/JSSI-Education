package project.jssi_education.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jssi_education.entity.*;
import project.jssi_education.repository.IAttendanceRepository;
import project.jssi_education.repository.ITeacherEvaluationRepository;
import project.jssi_education.service.impl.GradeService;
import project.jssi_education.service.impl.GroupCourseService;
import project.jssi_education.service.impl.StudentService;

import java.io.Serializable;

@Service
public class Enrollment implements Serializable {

    private final GradeService gradeService;
    private final IAttendanceRepository attendanceRepository;
    private final ITeacherEvaluationRepository teacherEvaluationRepository;
    private final GroupCourseService groupCourseService;
    private final StudentService studentService;

    @Autowired
    public Enrollment(GradeService gradeService, IAttendanceRepository attendanceRepository, ITeacherEvaluationRepository teacherEvaluationRepository, GroupCourseService groupCourseService, StudentService studentService) {
        this.gradeService = gradeService;
        this.attendanceRepository = attendanceRepository;
        this.teacherEvaluationRepository = teacherEvaluationRepository;
        this.groupCourseService = groupCourseService;
        this.studentService = studentService;
    }

    public void EnrollmentCourse(int studentId, Long groupCourseId){
        Student student = studentService.findByIdNumber(studentId);
        GroupCourse groupCourse = groupCourseService.findById(groupCourseId);
        Attendance attendance = createAttendance(student, groupCourse);
        Grade grade = createGrade(student, groupCourse);
        TeacherEvaluation teacherEvaluation = createTeacherEvaluation(student, groupCourse);
    }

    private Attendance createAttendance(Student student, GroupCourse groupCourse){
        Attendance attendance = new Attendance();
        attendance.setStudent(student);
        attendance.setGroupHasCourse(groupCourse);
        attendanceRepository.save(attendance);
        return attendance;
    }

    private Grade createGrade(Student student, GroupCourse groupCourse){
        Grade grade = new Grade();
        grade.setStudent(student);
        grade.setGroupHasCourse(groupCourse);
        gradeService.insert(grade);
        return grade;
    }

    private TeacherEvaluation createTeacherEvaluation(Student student, GroupCourse groupCourse){
        TeacherEvaluation teacherEvaluation = new TeacherEvaluation();
        teacherEvaluation.setStudent(student);
        teacherEvaluation.setGroupHasCourse(groupCourse);
        teacherEvaluationRepository.save(teacherEvaluation);
        return teacherEvaluation;
    }
}
