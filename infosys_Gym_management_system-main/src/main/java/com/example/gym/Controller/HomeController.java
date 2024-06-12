package com.example.gym.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.gym.entity.Userdetails;
import com.example.gym.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/newUser")
    public String saveUser(@RequestParam("username") String username,
                           @RequestParam("email") String email,
                           @RequestParam("pnumber") long pnumber,
                           @RequestParam("password") String password,
                           @RequestParam("confirmPassword") String confirmPassword,
                           RedirectAttributes redirectAttributes) {
        boolean emailExists = userService.checkEmail(email);
        if (emailExists) {
            redirectAttributes.addFlashAttribute("message", "Email already exists");
            return "redirect:/register";
        }

        if (!password.equals(confirmPassword)) {
            redirectAttributes.addFlashAttribute("message", "Password and confirm password do not match");
            return "redirect:/register";
        }

        Userdetails user = new Userdetails();
        user.setUsername(username);
        user.setEmail(email);
        user.setPnumber(pnumber);
        user.setPassword(password);
        Userdetails entry = userService.saveUser(user);
        if (entry != null) {
            redirectAttributes.addFlashAttribute("message", "Registration successful!");
            return "redirect:/login";
        } else {
            redirectAttributes.addFlashAttribute("message", "Something went wrong");
            return "redirect:/register";
        }
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @PostMapping("/loginUser")
    public String loginUser(@RequestParam("email") String email,
                            @RequestParam("password") String password,
                            HttpSession session,
                            RedirectAttributes redirectAttributes) {
        boolean isValidUser = userService.validateUser(email, password);
        if (isValidUser) {
            session.setAttribute("email", email);
            redirectAttributes.addFlashAttribute("message", "Login Successful!");
            return "redirect:/dashboard";
        } else {
            redirectAttributes.addFlashAttribute("message", "Invalid email or password");
            return "redirect:/login";
        }
    }
  


    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        if (session.getAttribute("email") != null) {
            return "dashboard";
        } else {
            return "redirect:/login";
        }
    }
}
