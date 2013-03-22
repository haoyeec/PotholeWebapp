package controller;

import model.Model;
import model.PotholeDAO;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class IndexAction extends ActionSupport{
	private static PotholeDAO potholeDAO;
	
	public void setDAO(Model model){
		potholeDAO = model.getPotholeDAO();
	}
	
	public String execute() throws Exception{
		ActionContext.getContext().getValueStack().push(potholeDAO.getAll());	
		return SUCCESS;

	}
}
