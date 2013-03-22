package controller;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;

import model.Model;


public class ApplicationListener implements ServletContextListener{
	private Model model;

	public void contextInitialized(ServletContextEvent event) {
		ServletContext sc = event.getServletContext();
		try {
			model = new Model(sc);
			RegisterAction regAct = new RegisterAction();
			regAct.setDAO(model);
			LoginAction logAct = new LoginAction();
			logAct.setDAO(model);
			EditAction editAct = new EditAction();
			editAct.setDAO(model);
			UploadAction upAct = new UploadAction();
			upAct.setDAO(model);
			ListPotholesAction listAct = new ListPotholesAction();
			listAct.setDAO(model);
			ViewPotholeAction viewAct = new ViewPotholeAction();
			viewAct.setDAO(model);
			ViewUserAction useAct = new ViewUserAction();
			useAct.setDAO(model);
			IndexAction indAct = new IndexAction();
			indAct.setDAO(model);
			ForgetAction forAct = new ForgetAction();
			forAct.setDAO(model);
			ListUsersAction listUsersAct = new ListUsersAction();
			listUsersAct.setDAO(model);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void contextDestroyed(ServletContextEvent event){
		System.gc();
	}
}
