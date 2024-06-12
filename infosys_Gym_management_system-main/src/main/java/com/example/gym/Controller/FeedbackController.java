package com.example.gym.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.gym.entity.Feedback;
import com.example.gym.repository.FeedbackRepository;

import java.util.Date;

@Controller
public class FeedbackController {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @GetMapping("/feedback")
    public String showFeedbackForm(Model model) {
        model.addAttribute("feedback", new Feedback());
        return "feedback";
    }

    @PostMapping("/feedback")
    public String submitFeedback(Feedback feedback) {
        feedback.setCreatedAt(new Date());
        feedbackRepository.save(feedback);
        return "redirect:/feedback/thankyou";
    }

    @GetMapping("/feedback/thankyou")
    public String showThankYouPage() {
        return "thankyou";
    }
}
