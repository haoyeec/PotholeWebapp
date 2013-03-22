package controller;

import model.HateDAO;
import model.Model;
import model.PersonDAO;
import model.PotholeDAO;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import databean.HateBean;
import databean.PersonBean;

public class ViewUserAction extends ActionSupport{
	private PersonBean personBean;
	private static HateDAO hateDAO;
	private static PersonDAO personDAO;
	private static PotholeDAO potholeDAO;
	
	private class Pair{
		private PersonBean innerPersonBean;
		private HateBean[] hateBeans;
		private String[] potholeNames;
		
		public PersonBean getInnerPersonBean() {
			return innerPersonBean;
		}
		public void setInnerPersonBean(PersonBean innerPersonBean) {
			this.innerPersonBean = innerPersonBean;
		}
		public HateBean[] getHateBeans() {
			return hateBeans;
		}
		public void setHateBeans(HateBean[] hateBeans) throws Exception{
			this.hateBeans = hateBeans;
			potholeNames = new String[hateBeans.length];
			for(int i = 0; i < hateBeans.length;i++){
				potholeNames[i] = potholeDAO.lookup(hateBeans[i].getPotholeId()).getDescription();
			}
		}
		
		public Pair(PersonBean innerPersonBean, HateBean[] hb)throws Exception{
			setInnerPersonBean(innerPersonBean);
			setHateBeans(hb);
		}
		public String[] getPotholeNames() {
			return potholeNames;
		}
	}
	
	public void setDAO(Model model){
		hateDAO = model.getHateDAO();
		personDAO = model.getPersonDAO();
		potholeDAO = model.getPotholeDAO();
	}
	public void validate(){
		try{
			int userId = 0;
			try{
				userId = Integer.parseInt(((String[])ActionContext.getContext().getParameters().get("userId"))[0]);
				personBean = personDAO.lookup(userId);
			}
			catch(Exception e){

				if(personBean == null){
					userId = (Integer) ActionContext.getContext().getSession().get("viewUser");
					personBean = personDAO.lookup(userId);
					if(personBean == null){
						System.out.println("Not happen");
						addActionError("No such user");
					}
				}
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
			addActionError("No such user");
		}
	}
	
	public String execute() throws Exception{
		PersonBean currUser = (PersonBean) ActionContext.getContext().getSession().get("currUser");
		if(currUser == null){
			addActionError("You need to login");
			ActionContext.getContext().getSession().put("redirect", "viewUser");
			ActionContext.getContext().getSession().put("viewUser", personBean.getUserId());
			return "login";
		}
		ActionContext.getContext().getSession().put("viewUser", null);
		Pair p = new Pair(personBean, hateDAO.lookupByUser(personBean));
		ActionContext.getContext().getValueStack().push(p);
		return SUCCESS;
	}
}
