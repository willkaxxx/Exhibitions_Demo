package ua.willkaxxx.demo.controllers;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ua.willkaxxx.demo.entity.Role;
import ua.willkaxxx.demo.entity.User;
import ua.willkaxxx.demo.repository.UserRepository;

@Controller
public class RegistrationController {

    private final UserRepository userRepository;

    public RegistrationController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("roles", Role.values());
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Model model) {
        model.addAttribute("roles", Role.values());
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            model.addAttribute("message", "UserExists");
            return "auth/registration";
        }
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepository.save(user);
        model.addAttribute("message", "You was successfully registered!");
        return "auth/registration";
//        return "redirect:/login";
    }
}
