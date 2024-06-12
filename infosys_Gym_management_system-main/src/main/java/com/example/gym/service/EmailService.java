package com.example.gym.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.MailException;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    public String sendEmail(String to, String subject, String inquiryType) {
        String senderEmail = "donvarshneydon@gmail.com";
        String text = "Thank you for reaching out!\n\n";
        text += "We have received your inquiry regarding " + inquiryType + ".\n";
        text += "We will get back to you as soon as possible.\n\n";
        text += "Best regards,\nYour Company Name";

        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(senderEmail);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);

            emailSender.send(message);
            return "Mail successfully sent";
        } catch (MailException | MessagingException e) {
            e.printStackTrace();
            return "Error sending mail: " + e.getMessage();
        }
    }
}
