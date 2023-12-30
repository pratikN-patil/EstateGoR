package com.example.realEstateGo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailNotificationService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendNotification(String toEmail, String subject, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(toEmail);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);

        javaMailSender.send(mailMessage);
    }
    
    public void sendWelcomeEmail(String to, String userName) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Welcome to RealEstateGo!");
        message.setText("Dear " + userName + ",\n\nWelcome to RealEstateGo! \nWe are thrilled to have you as a part of our community. Your registration is complete, and you are now a valued member of our platform.\r\n"
        		+ "\r\n"
        		+ "At RealEstateGo, we strive to provide you with a seamless experience in exploring and managing real estate properties. Whether you are a customer, agent, or property owner, we have tailored features to meet your specific needs.\r\n"
        		+ "\r\n"
        		+ "Feel free to log in to your account and start exploring the exciting world of real estate. If you have any questions or need assistance, our support team is here to help you.\r\n"
        		+ "\r\n"
        		+ "Thank you for choosing RealEstateGo. We look forward to assisting you in finding your dream property or connecting with potential clients.\r\n"
        		+ "\r\n"
        		+ "Best regards,\r\n\n"
        		+ "The RealEstateGo Team");

        javaMailSender.send(message);
    }
    
    public void NewAppointmentEmail(String to, String userName,String date,String address) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("New Appointment Added ");
        message.setText("Dear " + userName + ",\n\n"
        		+ "\r\n"
        		+ "We hope this message finds you well. We are pleased to confirm your upcoming appointment with us. Details are as follows:\r\n"
        		+ "\r\n"
        		+ "Date: "+date+" \r\n"
        		+ "Time: Approx 12:00pm\r\n"
        		+ "Location: "+address+"\r\n"
        		+ "If you have any questions or need to reschedule, please feel free to contact us at [Your Contact Information].\r\n"
        		+ "\r\n"
        		+ "We look forward to meeting with you. Thank you for choosing our services!\r\n"
        		+ "\r\n"
        		+ "Best regards,\r\n"
        		+ "\r\n"
        		+ "The RealEstateGo Team\r\n"
        		+ "Email : Enjoyfulllife2024@gmail.com");

        javaMailSender.send(message);
    }
}
