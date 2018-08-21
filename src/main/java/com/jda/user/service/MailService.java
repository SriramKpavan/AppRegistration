package com.jda.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class MailService {

	@Autowired
	private  JavaMailSender mailSender;
	
	public void sendMail(String email, String subject, String body) {
		// TODO Auto-generated method stub
		
		SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
		passwordResetEmail.setFrom("shravanbossu@gmail.com");
		passwordResetEmail.setTo(email);
		passwordResetEmail.setSubject(subject);
		passwordResetEmail.setText(body);
		
		mailSender.send(passwordResetEmail);
	}

	
}
