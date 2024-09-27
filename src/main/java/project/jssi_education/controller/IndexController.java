package project.jssi_education.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.jssi_education.entity.Course;
import project.jssi_education.entity.Group;
import project.jssi_education.entity.Teacher;
import project.jssi_education.entity.User;
import project.jssi_education.service.impl.GroupService;
import project.jssi_education.service.impl.TeacherService;
import project.jssi_education.service.impl.UserService;
import project.jssi_education.util.PasswordUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {
    @Autowired
    private UserService userService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private GroupService groupService;

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model,
                        HttpSession session) {
        List<User> users = userService.findAll();
        for (User user : users) {

            if (user.getUserName().equals(username) && user.getPassword().equals(password)) {
                // Almacena el usuario en la sesión
                session.setAttribute("loggedInUser", user);

                if (user.getRole().equals("student")) {
                    return "redirect:/indexStudent.html";
                }

                if (user.getRole().equals("manager"))
                    return "redirect:/indexManager.html";
                }

                if (user.getRole().equals("teacher")) {
                    Teacher teacher = teacherService.findByUserId(user.getId());
                    List<Group> groups = groupService.findByTeacher(teacher);

                    model.addAttribute("groups", groups);

                    Map<Long, List<Course>> coursesMap = new HashMap<>();
                    for (Group group : groups) {
                        List<Course> courses = groupService.findCoursesByGroup(group);
                        coursesMap.put(group.getGroupId(), courses);
                    }
                    model.addAttribute("courses", coursesMap);

                    return "indexTeacher";
                }

                if (user.getRole().equals("administrator"))
                    return "redirect:/indexAdmin.html";
                }
            }
        }
        return "redirect:/index.html";
    }

}
