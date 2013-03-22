package controller;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Model;
import model.PersonDAO;

import com.opensymphony.xwork2.ActionSupport;

import databean.PersonBean;

public class ForgetAction extends ActionSupport{
	private boolean emailButton = false;
	private String email;
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	private static PersonDAO personDAO;
	
	public boolean getEmailButton() {
		return emailButton;
	}

	public void setEmailButton(boolean forgetButton) {
		this.emailButton = true;
	}

	public void setDAO(Model model){
		personDAO = model.getPersonDAO();
	}
	
	public boolean isValidEmail(String s){
		if (s == null) return false;
		Pattern p = Pattern.compile("(.+)@(.+)");
		Matcher m = p.matcher(s);
		
		return m.matches() && (!(s.matches(".*[<>\"].*")));
	}
	
	public void validate(){
		if(!isValidEmail(email)){
			addFieldError("email", "Not valid email");
		}
	}
	public String execute() throws Exception{
		PersonBean user = new PersonBean();
		user.setEmail(email);
		user = personDAO.lookupByEmail(user);
		if(user == null){
			addFieldError("email", "No such email address");
			return INPUT;
		}
		else{
			//send email
			String to = user.getEmail();
			String from = "haoyeec@andrew.cmu.edu";
			String host = "localhost";
			Properties p = System.getProperties();
			p.setProperty("mail.smtp.host", host);
			
			Session session = Session.getDefaultInstance(p);
			try{
		         // Create a default MimeMessage object.
		         MimeMessage message = new MimeMessage(session);

		         // Set From: header field of the header.
		         message.setFrom(new InternetAddress(from));

		         // Set To: header field of the header.
		         message.addRecipient(Message.RecipientType.TO,
		                                  new InternetAddress(to));

		         // Set Subject: header field
		         message.setSubject("This is the Subject Line!");

		         // Now set the actual message
		         message.setText("This is actual message");

		         // Send message
		         Transport.send(message);
		         System.out.println("Sent message successfully....");
		      }catch (MessagingException mex) {
		         mex.printStackTrace();
		      }
		
			return SUCCESS;
		}
	}
}
