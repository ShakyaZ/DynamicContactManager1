package com.work.demo.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MyUtil {

	@Autowired

	private JavaMailSender javaMailSender;

	public void sendEmailToUs(String receivedemail, String subject, String body, String body2) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(receivedemail);
		msg.setSubject(subject + "(" + body + ")");
		msg.setText(body2);

		javaMailSender.send(msg);

	}
}
