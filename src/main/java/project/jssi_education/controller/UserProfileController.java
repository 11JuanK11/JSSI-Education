package project.jssi_education.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.jssi_education.entity.User;
import project.jssi_education.repository.IUserRepository;
import project.jssi_education.util.PasswordUtil;

@Controller
public class UserProfileController {

    @Autowired
    private IUserRepository userService;

    @GetMapping("/profile")
    public String viewProfile(Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {
            return "redirect:/index.html";
        }

        model.addAttribute("user", loggedInUser);
        return "profile";
    }

    @PostMapping("/update-profile")
    public String updateProfile(@RequestParam("name") String name,
                                @RequestParam("lastname") String lastname,
                                @RequestParam("email") String email,
                                HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser != null) {
            loggedInUser.setName(name);
            loggedInUser.setLastname(lastname);
            loggedInUser.setEmail(email);
            userService.save(loggedInUser);
            session.setAttribute("loggedInUser", loggedInUser);
        }

        return "redirect:/profile";
    }

    @PostMapping("/update-password")
    public String updatePassword(@RequestParam("oldPassword") String oldPassword,
                                 @RequestParam("newPassword") String newPassword,
                                 HttpSession session, RedirectAttributes redirectAttributes) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser != null) {
            if (loggedInUser.getPassword().equals(PasswordUtil.hashPassword(oldPassword))) {
                loggedInUser.setPassword(PasswordUtil.hashPassword(newPassword));
                userService.save(loggedInUser);
                session.setAttribute("loggedInUser", loggedInUser);
                redirectAttributes.addFlashAttribute("passwordMessage", "Password updated successfully!");
            } else {
                redirectAttributes.addFlashAttribute("passwordMessage", "Incorrect current password.");
            }
        }

        return "redirect:/profile";
    }
}
