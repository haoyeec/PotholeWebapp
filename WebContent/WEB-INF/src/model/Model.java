package model;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import org.mybeans.factory.BeanTable;

public class Model {
	private PersonDAO  personDAO;
	private PotholeDAO potholeDAO;
	private HateDAO hateDAO;
	private CommentDAO commentDAO;

	public Model(ServletContext config) throws ServletException {
				
		String jdbcDriver = config.getInitParameter("jdbcDriverName");
		String jdbcURL    = config.getInitParameter("jdbcURL");
		
		BeanTable.useJDBC(jdbcDriver,jdbcURL);		
		personDAO  = new PersonDAO();
		potholeDAO = new PotholeDAO();
		hateDAO = new HateDAO();
		commentDAO = new CommentDAO();
	}
	
	public PersonDAO  getPersonDAO()  { return personDAO;  }
	public PotholeDAO getPotholeDAO() {return potholeDAO; }
	public HateDAO getHateDAO() {return hateDAO;}
	public CommentDAO getCommentDAO() {return commentDAO;}
}
