package com.ey.bbms.mapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class BaseMapper {
	protected boolean findColumnNames(ResultSet rs, String columnLevel) throws SQLException {
	    if (rs == null) {
	      return false;
	    }
	    ResultSetMetaData rsMetaData = rs.getMetaData();
	    int numberOfColumns = rsMetaData.getColumnCount();
	    boolean Fo=false;
	    // get the column names; column indexes start from 1
	    for (int i = 1; i < numberOfColumns + 1; i++) {
	      String columnName = rsMetaData.getColumnName(i);
	      if(columnName.equalsIgnoreCase(columnLevel)){
	    	  Fo=true;
	    	  break;
	      }
	    }
	    
	    return Fo;
	}
	
	protected int findColumnAlias(ResultSet rs, String columnLevel) throws SQLException {
	    if (rs == null) {
	      return 0;
	    }
	    ResultSetMetaData rsMetaData = rs.getMetaData();
	    int numberOfColumns = rsMetaData.getColumnCount();
	    int Fo=0;
	    // get the column names; column indexes start from 1
	    for (int i = 1; i < numberOfColumns + 1; i++) {
	      String columnName = rsMetaData.getColumnLabel(i);
	      if(columnName.equalsIgnoreCase(columnLevel)){
	    	  Fo=i;
	    	  break;
	      }
	    }
	    
	    return Fo;
	}
	
	protected SimpleDateFormat DateFormate(){
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		return df;
	}
	
	protected SimpleDateFormat TimeFormate(){
		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
		return df;
	}
}
