package com.todoproject;

import java.util.Properties;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailNotifiaction {
	

	static void sendotpemail(String em,String n) {
		String senderEmailId = "write here your own mail id";
		String senderPass ="mail id password";
		String emailSmtpServer = "smtp.gmail.com";
		String emailServerPort = "465";
		
		Properties properties = new Properties();
		properties.put("mail.smtp.user", senderEmailId);
		properties.put("mail.smtp.host", emailSmtpServer);
		properties.put("mail.smtp.port", emailServerPort);
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.socketFactory.port", emailServerPort);
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.put("mail.smtp.socketFactory.fallback", "false");

		javax.mail.Session session = javax.mail.Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
			protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
				return new javax.mail.PasswordAuthentication(senderEmailId, senderPass);
			}
		});
		try {

			javax.mail.Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(senderEmailId));
			message.setRecipients(MimeMessage.RecipientType.TO, InternetAddress.parse(em));
			message.setSubject("Verification");
			message.setText(n);
			Transport.send(message);
			
		}

		catch (Exception e) {
			e.printStackTrace();
			System.out.println("error in try");

		}

	}
	
}


