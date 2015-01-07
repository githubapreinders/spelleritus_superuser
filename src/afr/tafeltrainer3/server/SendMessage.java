package afr.tafeltrainer3.server;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import afr.tafeltrainer3.shared.SuperUser;


public class SendMessage 
{
private SuperUser superuser;	

	public SendMessage(SuperUser superuser)
	{
		this.superuser = superuser;
		sendMail("apreinders74@gmail.com",superuser.getEmail(),"apreinders74@gmail.com",
				"Verslag Spelleritus",createMessage());
	}
		
	public SendMessage(String name,String emailadress, String password)
		{
			String message = "<B>Tijdelijk wachtwoord</B><BR><BR>Beste "+ name +" Wij hebben een tijdelijk wachtwoord "
					+ "voor u aangemaakt. Log in als begeleider en vul dit wachtwoord in. Daarna kunt "
					+ "u in het administratiescherm uw naam aanklikken en uw wachtwoord opnieuw instellen. "
					+ "<BR><BR>Uw tijdelijke wachtwoord is :<B> " + password + "</B><BR><BR>Met "
							+ "vriendelijke groet,  tafeltrainer gebruikersservice. ";
			sendMail("apreinders74@gmail.com",emailadress,"apreinders74@gmail.com",
					"Spelleritus tijdelijk wachtwoord",message);
			
		}

	public String sendMail(String from, String to, String replyTo, String subject, String message) {
	       
			String output=null;
			Properties props = new Properties();
	        Session session = Session.getDefaultInstance(props, null);

	        try {
		        	Multipart mp = new MimeMultipart();
		        	MimeBodyPart htmlpart = new MimeBodyPart();
		        	htmlpart.setContent(message,"text/html");
		        mp.addBodyPart(htmlpart); 	
	            Message msg = new MimeMessage(session);
	            msg.setFrom(new InternetAddress(from, "Spelleritus gebruikersservice"));
	            msg.addRecipient(Message.RecipientType.TO,
	                             new InternetAddress(to, "Gebruiker Spelleritus"));
	            msg.setSubject(subject);
	            msg.setContent(mp);
	            msg.setReplyTo(new InternetAddress[]{new InternetAddress(replyTo)});
	            Transport.send(msg);

	        } 
	        catch (AddressException e) 
	    		{
	            e.printStackTrace();
	        }
	         catch (MessagingException e) 
	        {
	        	 	e.printStackTrace();
	        } 
	        catch (UnsupportedEncodingException e) 
	        {
				e.printStackTrace();
			}
	        catch (Exception e) 
	        {
	        		output=e.toString();                
	        }   
	return output;
	}
	
	public String createMessage()
	{
		//TODO logo in de mail plaatsen en extra styling 
		String message = "<B>Resultaten Tafeltrainer gebruikersservice.</B><br><p>"
				+ "Beste "+superuser.getName()+" ,<br>Hierbij de laatste resultaten van uw leerling(en) op de "
						+ "tafeltrainer. U kunt uw instellingen altijd wijzigen via het gebruikersmenu op"
						+ " de <A href = 'http:1-dot-subtle-reserve-547.appspot.com'>site.</A><BR> Met vriendelijke groet,"
						+ "<br>team Tafeltrainer<br><p align = 'right'><I><A href = 'mailto:apreinders74@gmail.com'>Stuur ons een mail...</A></I></p><BR>";
		message += " <table border='0' cellpadding='0' cellspacing='0' width='900'>";
		String str = "<tr><th width='150'>naam</th><th width='100'>opgaven</th><th width='100'>score</th><th width='100'>snelheid</th>"
				+ "<th width='55'>t2</th><th width='55'>t3</th><th width='55'>t4</th><th width='55'>t5</th><th width='55'>t6</th><th width='55'>t7</th><th width='55'>t8</th><th width='65'>t9</th>";
		message+=str;
		
				
		return message;
}

		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
