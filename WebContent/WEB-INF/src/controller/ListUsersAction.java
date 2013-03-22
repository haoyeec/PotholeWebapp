package controller;

import java.util.Arrays;
import java.util.Comparator;

import model.Model;
import model.PersonDAO;
import model.PotholeDAO;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import databean.PersonBean;

public class ListUsersAction extends ActionSupport{
	private PersonBean[] allUsers;
	private static PersonDAO personDAO;
	private class PersonComparator implements Comparator{
		public int compare(Object o1, Object o2){
			PersonBean p1 = (PersonBean) o1;
			PersonBean p2 = (PersonBean) o2;
			return p1.getUserName().compareToIgnoreCase(p2.getUserName());
		}
		
		
	}
	
	public void setDAO(Model model){
		personDAO = model.getPersonDAO();
	}
	
	public String execute() throws Exception{
		allUsers = personDAO.getAll();
		Arrays.sort(allUsers, new PersonComparator());
		ActionContext.getContext().getValueStack().push(allUsers);
		return SUCCESS;
	}
}
