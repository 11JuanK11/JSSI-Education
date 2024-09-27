package project.jssi_education.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.jssi_education.entity.User;
import project.jssi_education.repository.IUserRepository;
import project.jssi_education.util.PasswordUtil;

@RestController
@RequestMapping("/api")
public class PasswordRecoveryController {

    @Autowired
    private IUserRepository userRepository;

    @PostMapping("/recover-password")
    public String recoverPassword(@RequestParam("username") String username) {
        User user = userRepository.findByUserName(username);
        if (user != null) {
            return "Password recovery initiated. You can now change your password.";
        }
        return "User not found";
    }


    @PostMapping("/change-password")
    public String changePassword(@RequestParam("username") String username,
                                 @RequestParam("newPassword") String newPassword) {
        User user = userRepository.findByUserName(username);
        if (user != null) {
            user.setPassword(PasswordUtil.hashPassword(newPassword));
            userRepository.save(user);
            return "Password changed successfully.";
        }
        return "User not found";
    }
}
