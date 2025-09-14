package com.jamersc.springboot.hcm_system.service.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private final JavaMailSender mailSender;

    public String fromEmail = "mr.catalla28@gmail.com";

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendWelcomeEmail(String toEmail, String username) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(toEmail);
        message.setSubject("Welcome to our HCM Job Portal, "  + username + "!");
        message.setText("Dear " + username + ",\n\n"
                + "Thank you for registering for our HCM portal. We're excited to have you!\n\n"
                + "You can now log in and start applying for jobs.\n\n"
                + "Best regards,\n"
                + "The HCM Team"
        );

        mailSender.send(message); // inject the email service to auth service > registerNewUserAndApplicant()
    }

    public void sendSubmittedApplicationEmail(String toEmail, String fullName, Long applicationId, String jobPosition) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(toEmail);
        message.setSubject("Submitted Application for " + jobPosition);
        message.setText("Hi " + fullName + ",\n\n"
                + "Thank you for applying "
                + "Applicant Details:"
                + "Name: " + fullName
                + "Application ID :\n" + applicationId
                + "JobTitle " + jobPosition
                + "Email: " + toEmail
        );

        mailSender.send(message);
    }
}
