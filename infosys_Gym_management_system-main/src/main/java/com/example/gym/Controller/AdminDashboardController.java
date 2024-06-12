// src/main/java/com/example/gym/controller/AdminDashboardController.java
package com.example.gym.Controller;

import com.example.gym.entity.Trainer;
import com.example.gym.repository.SlotRepository;
import com.example.gym.repository.TrainerRepository;
import com.example.gym.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminDashboardController {

    @Autowired
    private SlotRepository slotRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TrainerRepository trainerRepository;

    private final String adminUsername = "admin";
    private final String adminPassword = "admin123";

    @GetMapping("/login")
    public String showLoginPage() {
        return "admin-login";
    }

    @PostMapping("/login")
    public String handleLogin(@RequestParam String adminusername, @RequestParam String password, Model model) {
        if (adminUsername.equals(adminusername) && adminPassword.equals(password)) {
            return "redirect:/admin/dashboard";
        } else {
            model.addAttribute("error", true);
            return "admin-login";
        }
    }

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        model.addAttribute("slots", slotRepository.findAll());
        model.addAttribute("customers", userRepository.findAll());
        model.addAttribute("trainers", trainerRepository.findAll());
        return "admindashboard";
    }

    @PostMapping("/addTrainer")
    public String addTrainer(@RequestParam String name, @RequestParam String email, Model model) {
        Trainer trainer = new Trainer();
        trainer.setName(name);
        trainer.setEmail(email);
        trainerRepository.save(trainer);
        return "redirect:/admin/dashboard";
    }
}
