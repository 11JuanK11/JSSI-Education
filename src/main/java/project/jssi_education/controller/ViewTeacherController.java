package project.jssi_education.controller;


import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import project.jssi_education.entity.*;
import project.jssi_education.repository.IUserRepository;
import project.jssi_education.service.impl.TeacherService;

import java.util.List;

@Controller
public class ViewTeacherController {
    @Autowired
    private IUserRepository userService;

    @Autowired
    private TeacherService teacherService;

    @GetMapping("/schedule-teacher")
    public String schedule(Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        List<Group> groups = teacherService.getGroups(loggedInUser.getIdNumber());
        List<GroupCourse> groupsCourses = teacherService.getGroupsCourse(groups, null);
        model.addAttribute("groups", groupsCourses);
        model.addAttribute("userId", loggedInUser.getIdNumber());
        return "scheduleTeacher";
    }

    @GetMapping("/grade-teacher")
    public String grade(Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        model.addAttribute("userId", loggedInUser.getIdNumber());

        return "gradeTeacher";
    }




}
