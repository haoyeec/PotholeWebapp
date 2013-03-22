package controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LogoutAction extends ActionSupport{
	public String execute() throws Exception{
		// clear up the session
		for(String param : ActionContext.getContext().getSession().keySet()){
			ActionContext.getContext().getSession().put(param, null);
		}	
		return SUCCESS;

	}
}
