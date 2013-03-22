

package model;

import org.mybeans.dao.DAOException;
import org.mybeans.dao.GenericDAO;
import org.mybeans.factory.MatchArg;
import org.mybeans.factory.RollbackException;
import databean.PotholeBean;

public class PotholeDAO extends GenericDAO<PotholeBean>{
	public PotholeDAO(){
		super(PotholeBean.class, "haoyeec_pothole", "potholeId");
		getTable().setIdleConnectionCleanup(true);
		setUseAutoIncrementOnCreate(true);
		
	}
	
	
}