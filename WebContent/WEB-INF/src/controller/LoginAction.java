package controller;

import model.Model;
import model.PersonDAO;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import databean.PersonBean;

public class LoginAction extends ActionSupport{
	private String userName = null;
	private String password = null;
	private boolean loginButton = false;
	private boolean registerButton = false;
	private boolean forgetButton = false;
	private static PersonDAO personDAO;
	
	public void setUserName(String s) { userName = s.trim(); }
	public void setPassword(String s) { password = s.trim(); }
	public void setLoginButton(boolean b) {loginButton = true;} // fix for Struts2; Struts2 cannot convert String to bool
	public void setRegisterButton(boolean b) {registerButton = true;} // fix for Struts2; Struts2 cannot convert String to bool
	public void setForgetButton(boolean b) {forgetButton = true;}
	
	public String getUserName() { return userName;}
	public String getPassword() {return password;}
	public boolean getLoginButton() {return loginButton;}
	public boolean getRegisterButton() {return registerButton;}
	public boolean getForgetButton() {return forgetButton;}
	
	public void setDAO(Model model) {
		personDAO  = model.getPersonDAO();
	}
	
	
	@Override
	public void validate(){
		if(loginButton){
			if(ActionContext.getContext().getSession().get("currUser") != null){
				this.addActionError("already logged in");				
			}
			if(getUserName() != null && getUserName().trim().length() == 0){
				addFieldError("userName", "Username is required");
			}
			if(getPassword() != null && getPassword().trim().length()==0){
				addFieldError("password", "Password is required");
			}
		}
			
	}
	
	public String execute() throws Exception{
			if(loginButton){
				// check database
				PersonBean user = new PersonBean(0);
				user.setUserName(getUserName());
				user = personDAO.lookupByName(user);
				
				// if user does not exist
				if(user == null){
					addActionError("Wrong user or password");
				}
				else if (user.checkPassword(getPassword())){
					ActionContext.getContext().getSession().put("currUser", user);
					String redirect = (String) ActionContext.getContext().getSession().get("redirect");
					if(redirect == null){
						return SUCCESS;
					}
					else{
						ActionContext.getContext().getSession().put("redirect", null);
						return redirect;
					}
				}
				else addActionError("Wrong user or password");
				return "login";
			}
			if(registerButton){
				return "register";
			}
			if(forgetButton){
				return "forget";
			}
			
			return "login";
		
	}
}
