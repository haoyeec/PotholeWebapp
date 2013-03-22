package controller;

import java.util.Arrays;
import java.util.Comparator;

import model.Model;
import model.PotholeDAO;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import databean.PotholeBean;

public class ListPotholesAction extends ActionSupport{
	private PotholeBean[] allPotholes;
	private static PotholeDAO potholeDAO;
	private class PotholeComparator implements Comparator{
		public int compare(Object o1, Object o2){
			PotholeBean p1 = (PotholeBean) o1;
			PotholeBean p2 = (PotholeBean) o2;
			return p2.getHateCount() - p1.getHateCount();
		}
		
		
	}
	
	public void setDAO(Model model){
		potholeDAO = model.getPotholeDAO();
	}
	
	public String execute() throws Exception{
		allPotholes = potholeDAO.getAll();
		Arrays.sort(allPotholes, new PotholeComparator());
		ActionContext.getContext().getValueStack().push(allPotholes);
		return SUCCESS;
	}
}
