

package model;

import org.mybeans.dao.DAOException;
import org.mybeans.dao.GenericDAO;
import org.mybeans.factory.MatchArg;
import org.mybeans.factory.RollbackException;

import databean.HateBean;
import databean.PersonBean;
import databean.PotholeBean;

public class HateDAO extends GenericDAO<HateBean>{
	public HateDAO(){
		super(HateBean.class, "haoyeec_hate", "hateId");
		getTable().setIdleConnectionCleanup(true);
		setUseAutoIncrementOnCreate(true);
		
	}

	public boolean exists(PersonBean user, PotholeBean pothole) throws Exception{

		HateBean[] matches = getFactory().match(
				MatchArg.and(
						MatchArg.equals("userId", user.getUserId()),
						MatchArg.equals("potholeId", pothole.getPotholeId())
				)
		);
		
		return (matches.length != 0);

	}
	
	public HateBean[] lookupByUser(PersonBean user) throws Exception{
		return getFactory().match(MatchArg.equals("userId", user.getUserId()));
	}
	
}