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

public class sendVerificationMail
{
	private SuperUser superuser;	

		public sendVerificationMail(SuperUser superuser)
		{
			this.superuser = superuser;
			
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
			String message = "<B>Verificatie van uw emailadres.</B><br><p>"
					+ "Beste "+superuser.getName()+" , als laatste stap moet uw email-adres"
							+ "nog worden bevestigd. U doet dit door op onderstaande link te klikken."
							+ " Uw browser zal openen en er zal een boodschap komen dat u met "
							+ "succes de inschrijving heeft voltooid.<BR><BR>";
			String url = "<A href = 'http://spelleritus-superuser.appspot.com"+"?"+"codestring="+superuser.getVerificationcode()+"'>"+
					"link"+"</A>";	
					
			message = message + url;
			return message;
	}

			
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

}
