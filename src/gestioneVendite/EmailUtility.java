package gestioneVendite;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUtility {
	/**
	 * @author Pasquale Settembre
	 * <b>Permette l'invio di una email</b>
	 * @param host
	 * @param port
	 * @param userName
	 * @param password
	 * @param toAddress
	 * @param subject
	 * @param message
	 * @throws AddressException
	 * @throws MessagingException
	 */
	public static void sendEmail(String host, String port,
			final String userName, final String password, String toAddress,
			String subject, String message) throws AddressException,MessagingException {

		// sets SMTP server properties
		Properties properties = new Properties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.socketFactory.port", "465"); 		//SSL Port
		properties.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory"); 				//SSL Factory 
		properties.put("mail.smtp.port", port);
		properties.put("mail.smtp.auth", "true");

		// creates a new session with an authenticator
		Session session = Session.getInstance(properties,  
				 new javax.mail.Authenticator() {  
				  protected PasswordAuthentication getPasswordAuthentication() {  
					  System.out.println("AUTENTICANDO.."+userName+" "+password);
					  return new PasswordAuthentication(userName,password);  
				   }  
				});
			

		// creates a new e-mail message
		Message msg = new MimeMessage(session);

		msg.setFrom(new InternetAddress(userName));
		InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
		msg.setRecipients(Message.RecipientType.TO, toAddresses);
		msg.setSubject(subject);
		msg.setSentDate(new Date());
		msg.setText(message);

		// sends the e-mail
		Transport.send(msg);

	}
}
