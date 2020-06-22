package com.util;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {
	
	public void sendEmail(String recepient, String content) throws MessagingException {
		Properties props =  new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		String senderEmail = "tda101g2@gmail.com";
		String senderPassword = "tda101petbox";
		
		Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(senderEmail, senderPassword);
			}
		});
		
		Message msg = prepareMessage(session, senderEmail, recepient, content);
		
		Transport.send(msg);
	}

	private Message prepareMessage(Session session, String senderEmail, String recepient, String content) {
		try {
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(senderEmail));
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
			msg.setSubject("Petbox Website");
			msg.setContent(content, "text/html"); 
			return msg;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
