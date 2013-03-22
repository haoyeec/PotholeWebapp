

package model;

import org.mybeans.dao.DAOException;
import org.mybeans.dao.GenericDAO;
import org.mybeans.factory.MatchArg;
import org.mybeans.factory.RollbackException;
import databean.PersonBean;

public class PersonDAO extends GenericDAO<PersonBean>{
	public PersonDAO(){
		super(PersonBean.class, "haoyeec_user", "userId");
		getTable().setIdleConnectionCleanup(true);
		setUseAutoIncrementOnCreate(true);
		
	}
	
	public PersonBean lookupByName(PersonBean user) throws DAOException{
		try{
			PersonBean[] users = getFactory().match(MatchArg.equals("userName", user.getUserName()));
			if(users.length > 1) throw new DAOException("More than 1 user with same name");
			else if (users.length == 0) return null;
			else return users[0];
		}
		catch(RollbackException e){
			throw new DAOException(e);
		}
			
	}
	
	public PersonBean lookupByEmail(PersonBean user) throws DAOException{
		try{
			PersonBean[] users = getFactory().match(MatchArg.equals("email", user.getEmail()));
			if(users.length > 1) throw new DAOException("More than 1 user with same name");
			else if (users.length == 0) return null;
			else return users[0];
		}
		catch(RollbackException e){
			throw new DAOException(e);
		}
			
	}
	
	public void changePassword(PersonBean user, String p) throws DAOException{
		try{
			PersonBean actualUser = lookup(user.getUserId());
			actualUser.setPassword(p);
		}
		catch(DAOException e){
			throw e;
		}
	}
	
}
