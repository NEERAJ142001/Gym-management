package com.example.gym.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.gym.service.EmailService;

@Controller
public class ContactController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/contact-us")
    @ResponseBody
    public String handleContactForm(@RequestParam String from,
                                    @RequestParam String inquiryType) {
        try {
            String subject = "Thank you for reaching out";
            String message=emailService.sendEmail(from, subject, inquiryType);
            return "Email sent successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error sending email";
        }
    }
}

