package file.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


import file.entity.User;

public class MailSender {
	
	
	public void sendMailTo(User user) {
		final Properties prop=new Properties();
		prop.setProperty("mail.transport.protocol", "smtps");
		prop.setProperty("mail.smtps.auth", "true");
		prop.setProperty("mail.smtps.host", "smtp.gmail.com");
		prop.setProperty("mail.smtps.user", "testforme1324@gmail.com");
		Session mailSession=Session.getDefaultInstance(prop);
		MimeMessage message=new MimeMessage(mailSession);
		try {
			
		message.setFrom(new InternetAddress("testforme1324"));
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(user.getMail()));
		message.setSubject("Confirm message");
		message.setText("This is confirmation message.\nClick the link below to confirm your account:\n"
				+"http://localhost:8080/storefront/confirm/"+user.getConfirmToken()
				);
		}catch(AddressException e) {
			e.printStackTrace();
		}catch(MessagingException e) {
			e.printStackTrace();
		}
		Transport tr;
		try {
			tr = mailSession.getTransport();
			tr.connect(null,"FortestMe");
			tr.sendMessage(message, message.getAllRecipients());
			tr.close();
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(MessagingException e) {
			e.printStackTrace();
		}
		
		
		
		
	}
}

