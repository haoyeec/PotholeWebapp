package controller;

import model.Model;
import model.PersonDAO;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import databean.PersonBean;

public class EditAction extends ActionSupport{
	private PersonBean personBean;
	private static PersonDAO personDAO;
	private boolean passwordButton = false;
	
	public void setPasswordButton(boolean b) {passwordButton = true;}
	public boolean getPasswordButton() {return passwordButton;}
	public void setDAO(Model model){
		personDAO = model.getPersonDAO();
	}
	
	public String execute() throws Exception{
		personBean = (PersonBean) ActionContext.getContext().getSession().get("currUser");
		if (personBean == null){
			ActionContext.getContext().getSession().put("redirect", "edit");
			addActionError("You need to login");
			return "login";
		}
		else if (passwordButton){
			String oldPassword = ((String[])(ActionContext.getContext().getParameters().get("oldPassword")))[0];
			String newPassword = ((String[])(ActionContext.getContext().getParameters().get("newPassword")))[0];
			if(oldPassword != null && oldPassword != "" && newPassword != null && newPassword != "" && oldPassword.equals(newPassword)){
				personBean.setClearPassword(oldPassword);
				personDAO.update(personBean);
				PersonBean actualPerson = personDAO.lookup(personBean.getUserId());
				ActionContext.getContext().getSession().put("currUser", actualPerson);
				addActionError("Password changed");
				return SUCCESS;
			}
			else{
				addFieldError("oldPassword", "Invalid passwords");
				return "input";
			}
		}
		else
			return SUCCESS;
	}
}
