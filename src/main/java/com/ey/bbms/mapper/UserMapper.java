package com.ey.bbms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;


import org.springframework.jdbc.core.RowMapper;

import com.ey.bbms.model.main.HUser;

public class UserMapper extends BaseMapper implements RowMapper<HUser> {

	public HUser mapRow(ResultSet rs, int rowNum) throws SQLException {
	    HUser user = new HUser();
		
	    if (findColumnNames(rs, "user_id")) {
		user.setUser_id(rs.getInt("user_id"));
	    }
		
	    if (findColumnNames(rs, "user_name")) {
		user.setUser_name(rs.getString("user_name"));
	    }
		
	    if (findColumnNames(rs, "password")) {
		user.setPassword(rs.getString("password"));
	    }
	    
	    if (findColumnNames(rs, "full_name")) {
		user.setFull_name(rs.getString("full_name"));
	    }
	    
	    if (findColumnNames(rs, "roles")) {
		user.setRoles(rs.getString("roles"));
	    }
		
	    return user;
	}

}
