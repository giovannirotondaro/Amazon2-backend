package com.techzon.utilities;

import java.util.Properties;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class InvioMail {
	private String email;
	private String testoMail;
	private String oggettoMail;
	
	public InvioMail(String email,String testoMail,String oggettoMail) {
			this.email=email;
			this.testoMail=testoMail;
			this.oggettoMail=oggettoMail;
	}
	
	public void invia() {
		String from="techzon@libero.it";
        String to=email;
        String host="smtp.libero.it.";
        Properties properties=System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", true);
        Session session= Session.getInstance(properties,new javax.mail.Authenticator() {
        	protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("techzon@libero.it", "Techzon_2022");
			}
        });
        //la prossima istruzione serve per stampare il msg
        //session.setDebug(true);
        
    	try {
    		
          	MimeMessage message=new MimeMessage(session);
    		message.setFrom(new InternetAddress(from));
    		message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
    		message.setSubject(oggettoMail);
    		message.setText(testoMail);
    		Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void inviaHtml() {
		String from="techzon@libero.it";
        String to=email;
        String host="smtp.libero.it.";
        Properties properties=System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", true);
        Session session= Session.getInstance(properties,new javax.mail.Authenticator() {
        	protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("techzon@libero.it", "Techzon_2022");
			}
        });
        //la prossima istruzione serve per stampare il msg
        //session.setDebug(true);
        
    	try {
          	MimeMessage message=new MimeMessage(session);
    		message.setFrom(new InternetAddress(from));
    		message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
    		message.setSubject(oggettoMail);
    		//message.setText(testoMail);
    		MimeMultipart multipart = new MimeMultipart("related");

            BodyPart messageBodyPart = new MimeBodyPart();
            String htmlText = testoMail;
            messageBodyPart.setContent(htmlText, "text/html");
            multipart.addBodyPart(messageBodyPart);

            message.setContent(multipart);
  
    		Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
