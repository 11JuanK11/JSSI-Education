package project.jssi_education.controller;


import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import project.jssi_education.entity.Grade;
import project.jssi_education.entity.Group;
import project.jssi_education.entity.GroupCourse;
import project.jssi_education.entity.User;
import project.jssi_education.repository.IUserRepository;
import project.jssi_education.service.impl.GroupCourseService;
import project.jssi_education.service.impl.StudentService;
import project.jssi_education.service.impl.TeacherService;

import java.util.List;

@Controller
public class ViewStudentController {
    @Autowired
    private IUserRepository userService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private GroupCourseService groupCourseService;


    @GetMapping("/schedule-student")
    public String schedule(Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        List<Grade> grades = studentService.getGrades(loggedInUser.getIdNumber());
        List<GroupCourse> groupsCourses = groupCourseService.getGroupsCoursebyGrades(grades);
        model.addAttribute("groups", groupsCourses);
        model.addAttribute("userId", loggedInUser.getIdNumber());
        return "scheduleStudent";
    }

    @GetMapping("/grade-student")
    public String grade(Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        model.addAttribute("userId", loggedInUser.getIdNumber());

        return "gradeStudent";
    }




}
