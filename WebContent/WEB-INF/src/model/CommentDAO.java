

package model;

import org.mybeans.dao.DAOException;
import org.mybeans.dao.GenericDAO;
import org.mybeans.factory.MatchArg;
import org.mybeans.factory.RollbackException;

import databean.CommentBean;
import databean.PotholeBean;

public class CommentDAO extends GenericDAO<CommentBean>{
	public CommentDAO(){
		super(CommentBean.class, "haoyeec_comment", "commentId");
		getTable().setIdleConnectionCleanup(true);
		setUseAutoIncrementOnCreate(true);
		
	}
	
	public CommentBean[] lookupByPothole(PotholeBean pothole)throws Exception{
		return getFactory().match(MatchArg.equals("potholeId", pothole.getPotholeId()));
	}
}