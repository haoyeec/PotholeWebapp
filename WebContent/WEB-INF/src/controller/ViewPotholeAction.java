package controller;

import org.mybeans.dao.DAOException;

import model.CommentDAO;
import model.HateDAO;
import model.Model;
import model.PersonDAO;
import model.PotholeDAO;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import databean.CommentBean;
import databean.HateBean;
import databean.PersonBean;
import databean.PotholeBean;

public class ViewPotholeAction extends ActionSupport{
	// internal data
	private PotholeBean pothole;
	private class Pair{
		private PotholeBean pothole;
		private CommentBean[] comments;
		private String[] userNames;
		
		public PotholeBean getPothole() {
			return pothole;
		}
		public void setPothole(PotholeBean pothole) {
			this.pothole = pothole;
		}
		public CommentBean[] getComments() {
			return comments;
		}
		public void setComments(CommentBean[] comments) throws Exception{
			this.comments = comments;
			userNames = new String[comments.length];
			for(int i = 0; i < comments.length; i++){
				userNames[i] = personDAO.lookup(comments[i].getUserId()).getUserName();
			}
		}
		
		public String[] getUserNames(){
			return userNames;
		}
		
		public Pair(PotholeBean p, CommentBean[] c) throws Exception{
			setPothole(p);
			setComments(c);
		}
	}
	
	
	// DAOs
	private static PotholeDAO potholeDAO;
	private static HateDAO hateDAO;
	private static CommentDAO commentDAO;
	private static PersonDAO personDAO;
	
	// fields
	private int potholeId; //used for the hidden field
	private String review;
	private boolean hateButton = false;
	private boolean shareButton = false;
	
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	
	public void setHateButton(boolean b) {hateButton = true;}
	public boolean getHateButton() {return hateButton;}

	public void setShareButton(boolean b) {shareButton = true;}
	public boolean getShareButton(){return shareButton;}
	
	public int getPotholeId() {
		return potholeId;
	}

	public void setPotholeId(int potholeId) {
		this.potholeId = potholeId;
	}

	public void setDAO (Model model){
		potholeDAO = model.getPotholeDAO();
		hateDAO = model.getHateDAO();
		commentDAO = model.getCommentDAO();
		personDAO = model.getPersonDAO();
	}
	
	private boolean isValidComment(String s){
		if(s == null || s.equals("") || s.matches(".*[<>\"].*")) return false;
		return true;
	}
	
	public void validate(){
		
		// checks if potholeId is valid and available
		try{
			pothole = potholeDAO.lookup(potholeId);
			if(pothole == null) addActionError("No such pothole");
		}catch (Exception e){
			try{
				String[] array = ((String[])(ActionContext.getContext().getParameters().get("potholeId")));
				if(array == null) throw new Exception("no parameter");
				int potholeIdParam =  Integer.parseInt(
					array[0]
				);
			
				pothole = potholeDAO.lookup(potholeIdParam);
			}catch(Exception e1){
				addActionError("No such pothole");
			}
			
		}
		
	}
	public String execute() throws Exception{
		PersonBean currUser = (PersonBean) ActionContext.getContext().getSession().get("currUser");
		if(hateButton && !shareButton){
			
			
			if(currUser == null){
				addActionError("You must be logged in");
				return INPUT;
			}
			if(hateDAO.exists(currUser, pothole)){
				addActionError("You already hate this pothole!");
				return INPUT;
			}
			
			// update potholeDAO
			pothole.setHateCount(pothole.getHateCount() + 1);
			potholeDAO.update(pothole);
			
			PotholeBean actualPothole = potholeDAO.lookup(pothole.getPotholeId());
			
			// update hateDAO
			HateBean hateBean = new HateBean(0);
			hateBean.setUserId(currUser.getUserId());
			hateBean.setPotholeId(pothole.getPotholeId());
			hateDAO.create(hateBean);
			
			// admin
			Pair p = new Pair(actualPothole, commentDAO.lookupByPothole(actualPothole));
			ActionContext.getContext().getValueStack().push(p);
			addActionError("You hate this pothole!");
			return SUCCESS;
		}
		else if(shareButton && !hateButton){
			// prep for failure
			Pair p = new Pair(pothole, commentDAO.lookupByPothole(pothole));
			ActionContext.getContext().getValueStack().push(p);
			if(currUser == null){
				addActionError("You must be logged in");
				return INPUT;
			}
			if(!isValidComment(review)){
				addFieldError("review", "Comment is invalid");
				return INPUT;
			}
			// update commentDAO
			try{
			CommentBean newComment = new CommentBean();
			newComment.setPotholeId(pothole.getPotholeId());
			newComment.setUserId(currUser.getUserId());
			newComment.setComment(review);
			commentDAO.create(newComment);
			}
			catch(DAOException dao){
				addFieldError("review","Invalid comment: try shortening your comment!");
				return INPUT;
			}
			// admin
			p = new Pair(pothole, commentDAO.lookupByPothole(pothole));
			ActionContext.getContext().getValueStack().push(p);
			review = null;
			return SUCCESS;
		}
		else{
			Pair p = new Pair(pothole, commentDAO.lookupByPothole(pothole));
			ActionContext.getContext().getValueStack().push(p);
			return SUCCESS;
		}
		
	}
}
