package com.ey.bbms.dao.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.ey.bbms.utils.JdbcService;

/* ========================================
* Scooby v. 1.0 class library
* ========================================
*
* http://www.scooby.com
*
* (C) Copyright 2000-2010, by WebHawksIT.
*
* --------------------
* BaseDao.java
* --------------------
* Created on Jan 01, 2016
*
* $Revision: $
* $Author: $
* $Source: $
* $Id:  $
*
* Jan 01, 2016: Original version (Monsur)
*
*/ 
public class BaseDAO {
	private JdbcService jdbcService;
	
	public JdbcService getJdbcService() {
		return jdbcService;
	}


	public void setJdbcService(JdbcService jdbcService) {
		this.jdbcService = jdbcService;
	}

	/*protected String getCommonArgs() {

		return "deleted = 0, modifierid = 1";
	}*/
}
