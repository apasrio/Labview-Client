import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



public class EmailUtility {
	
	final String username = "TO_BE_COMPLETED";
	final String password = "TO_BE_COMPLETED";
	final String emailTo = "TO_BE_COMPLETED";
	private Properties props;
	
	public EmailUtility(){
		props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");		
	}
	
	public void sendEmail(String subject, String body){
		Session session = Session.getInstance(props,
				  new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				  });
		
		try {
			 
			Message message = new MimeMessage(session);
			//message.setFrom(new InternetAddress("alpedrete@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(emailTo));
			message.setSubject(subject);
			message.setText(body);
 
			Transport.send(message);
 
			System.out.println("Done");
 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

}
