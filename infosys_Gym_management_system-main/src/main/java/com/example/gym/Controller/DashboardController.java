package com.example.gym.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/about-us")
    public String aboutUs() {
        return "about-us"; // Assuming you have an "about-us.html" template
    }

    @GetMapping("/slot")
    public String slotBooking() {
        return "slot"; // Assuming you have a "slot-booking.html" template
    }

    @GetMapping("/plans")
    public String viewPlans() {
        return "plans"; // Assuming you have a "plans.html" template
    }

    @GetMapping("/contact-us")
    public String contactUs() {
        return "contact-us"; // Assuming you have a "contact-us.html" template
    }

    @GetMapping("/trainers")
    public String trainers() {
        return "trainers"; // Assuming you have a "trainers.html" template
    }

    @GetMapping("/feedback-from-dashboard")
    public String feedbackFromDashboard() {
        return "feedback"; // Assuming you have a "feedback.html" template
    }
}
