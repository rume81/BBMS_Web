/* ========================================
* Scooby v. 1.0 class library
* ========================================
*
* http://www.scooby.com
*
* (C) Copyright 2010-2020, by WebHawksIT.
*
* --------------------
* SessionService.java
* --------------------
* Created on Dec 5, 2016
*
* Revision: 
* Author: 
* Source: 
* Id:  
*
* Dec 5, 2016: Original version (Monsur)
*
*/
package com.ey.bbms.services.impl;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ey.bbms.dao.interfaces.ISessionDAO;
import com.ey.bbms.model.main.HUser;
import com.ey.bbms.model.main.Session;
import com.ey.bbms.model.main.UserSession;
import com.ey.bbms.services.interfaces.ISessionService;

/**
 * @author OS-10 Monsur
 *
 */
public class SessionService implements ISessionService {

    private final Logger logger = LoggerFactory.getLogger(SessionService.class);

    private ISessionDAO sessionDao;

    private UserSession userSession;

    public void setSessionDao(ISessionDAO sessionDao) {
	this.sessionDao = sessionDao;
    }

    public void setUserSession(UserSession userSession) {
	this.userSession = userSession;
    }

    public UserSession getUserSession() {
	return userSession;
    }

    public UserSession insertSession(HttpServletRequest request, HUser user) throws Exception {
	if (!isSessionValid()) {
	    // sets session info
	    Session session = new Session();
	    session.setId(-1);
	    session.setUser(user);
	    session.setReferrer(request.getHeader("referer"));
	    session.setBrowser(request.getHeader("user-agent"));
	    session.setSearchstring(request.getQueryString());

	    String requestUrl = request.getRequestURL().toString();
	    requestUrl = requestUrl.replaceAll("'", "''");
	    session.setLocation(requestUrl);
	    String ip = request.getRemoteAddr();
	    session.setClientIpAddress(ip);
	    Date date = new Date();

	    session.setSessionStart(date);
	    session.setSessionEnd(date);

	    // insert session info in DB
	    int sessionId = sessionDao.insertSession(session);

	    // update current web session
	    userSession.setSessionId(sessionId);
	    userSession.setClientIpAddress(ip);
	    userSession.setUser(user);
	} // session not set

	logger.info("User Session Id - > " + userSession.getSessionId());
	return userSession;
    }

    public void setUser(HUser user) {
	userSession.setUser(user);
    }

    public void setBidDate(String bidDate) {
	userSession.setBidDate(bidDate);
    }
    
    public String getBidDate() {
        return userSession.getBidDate();
    }

    public boolean isSessionValid() {
	return (userSession.getSessionId() > 0 ? true : false);
    }

    public void invalidateSession(int sessionId) {
	userSession.setSessionId(0);
	userSession.setBidDate("");
	userSession.setBidAmountPland("");
	userSession.setBorrowingDate("");
	userSession.setFootCutInterestRates("");
	userSession.setMaturityDate("");
    }

}
