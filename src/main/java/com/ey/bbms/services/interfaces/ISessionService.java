package com.ey.bbms.services.interfaces;

import javax.servlet.http.HttpServletRequest;

import com.ey.bbms.model.main.HUser;
import com.ey.bbms.model.main.UserSession;


public interface ISessionService {
    public UserSession insertSession(HttpServletRequest request,HUser user)  throws Exception;
	
    public boolean isSessionValid();

    public void invalidateSession(int sessionId);
	
    public UserSession getUserSession();
	
    //public void setEmployee(HEmployee emp);
}
