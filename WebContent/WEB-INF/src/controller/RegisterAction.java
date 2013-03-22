package controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.mybeans.dao.DAOException;

import model.Model;
import model.PersonDAO;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import databean.PersonBean;

public class RegisterAction extends ActionSupport{
	private String userName;
	private String zipCode;
	private String email;
	private String password;
	private PersonBean personBean;
	private static PersonDAO personDAO;

	public void setDAO(Model m) {personDAO = m.getPersonDAO();}
	
	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getZipCode() {
		return zipCode;
	}


	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public boolean isValidZipCode(int i){
		if (i >= 10000 && i < 99999)
			return true;
		else
			return false;
	}
	
	public boolean isValidEmail(String s){
		if (s == null) return false;
		Pattern p = Pattern.compile("(.+)@(.+)");
		Matcher m = p.matcher(s);
		
		return m.matches() && (!(s.matches(".*[<>\"].*")));
	}
	
	public boolean isValidUserName(String s)throws DAOException{
		if(s == null || s.matches(".*[<>\"].*")) return false;
		PersonBean newPerson = new PersonBean();
		newPerson.setUserName(s);
		if(personDAO.lookupByName(newPerson) != null){
			addFieldError("userName", "User name already in use");
			return false;
		}
		Pattern p = Pattern.compile(".+");
		return p.matcher(s).matches();	
	}
	
	public void validate(){
		try{
			try{
				if(!isValidZipCode(Integer.parseInt(getZipCode().trim()))){
					addFieldError("zipCode", "Invalid zipcode");
				}
			}catch (NumberFormatException nfe){
				addFieldError("zipCode", "Invalid zipcode");
			}

			if(!isValidEmail(getEmail().trim())){
				addFieldError("email", "Invalid email");
			}
			try{
				if(!isValidUserName(getUserName())){
					addFieldError("userName", "Invalid username");
				}
			}catch(DAOException e){
				addFieldError("personBean.userName", "Invalid username");
			}

			if(getPassword() == null || getPassword().equals("")){
				
				addFieldError("password", "Invalid password");
			}
		}


		catch(Exception e){
			addActionError("try again");
		}
	}
	
	public String execute() throws Exception{
		// try to store in database
		try{
			personBean = new PersonBean(0);
			personBean.setEmail(email);
			personBean.setUserName(userName);
			personBean.setClearPassword(password);
			personBean.setZipCode(zipCode);
			PersonBean actualUser = personDAO.create(personBean);
			ActionContext.getContext().getSession().put("currUser", actualUser);
			String redirect = (String)ActionContext.getContext().getSession().get("redirect");
			if(redirect == null){
				return SUCCESS;
			}
			else{
				ActionContext.getContext().getSession().put("redirect", null);
				return redirect;
			}
		}
		catch (DAOException e){
			addActionError(e.getMessage());
			return INPUT;
		}
		
		
	}
}
