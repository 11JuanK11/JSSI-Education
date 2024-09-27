package project.jssi_education.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;
import project.jssi_education.entity.User;
import project.jssi_education.repository.IUserRepository;

@Controller
public class UserProfileController {

    @Autowired
    private IUserRepository userService;

    // Este método permitirá acceder a la página de perfil mediante GET
    @GetMapping("/profile")
    public String viewProfile(Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {
            return "redirect:/index.html";
        }

        model.addAttribute("user", loggedInUser);
        return "profile";  // Nombre de la vista para mostrar el perfil
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
            userService.save(loggedInUser);  // Guardar cambios en la BD
            session.setAttribute("loggedInUser", loggedInUser); // Actualizar sesión
        }

        return "redirect:/profile";  // Redirigir a la vista de perfil
    }

    @PostMapping("/update-password")
    public String updatePassword(@RequestParam("oldPassword") String oldPassword,
                                 @RequestParam("newPassword") String newPassword,
                                 HttpSession session, Model model) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser != null) {
            if (loggedInUser.getPassword().equals(oldPassword)) {
                loggedInUser.setPassword(newPassword);
                userService.save(loggedInUser);
                session.setAttribute("loggedInUser", loggedInUser);
                model.addAttribute("passwordMessage", "Password updated successfully!");
            } else {
                model.addAttribute("passwordMessage", "Incorrect current password.");
            }
        }

        return "profile";  // Redirigir a la vista de perfil con mensaje de éxito o error
    }
}
