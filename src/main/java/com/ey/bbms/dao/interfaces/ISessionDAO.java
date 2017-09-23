package com.ey.bbms.dao.interfaces;

import com.ey.bbms.model.main.Session;


/**
 * @author Monsur
 * 
 */
public interface ISessionDAO {
	
	public int getSessionLastId();
	
	public int insertSession(Session session)  throws Exception;
}
