package project.jssi_education.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.jssi_education.entity.User;
import project.jssi_education.service.impl.UserService;

import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private UserService userService;


    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, Model model) {
        List<User> users = userService.findAll();
        for (User user: users){
            if(user.getUserName().equals(username) && user.getPassword().equals(password)){

                if(user.getRole().equals("student"))
                    return "redirect:/indexStudent.html";

                if(user.getRole().equals("manager"))
                    return "redirect:/indexManager.html";

                if(user.getRole().equals("teacher"))
                    return "redirect:/indexTeacher.html";

                if(user.getRole().equals("administrator"))
                    return "redirect:/indexAdmin.html";
            }
        }
        return "redirect:/index.html";
    }
}
