package com.comviva.frs.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {
	public static Connection con = getInstance();
	public static PreparedStatement ps;
	public static ResultSet rs;
	public static Connection getInstance() {
		// TODO Auto-generated method stub
		
		try
	    {
	    Class.forName("com.mysql.cj.jdbc.Driver");
	    con=DriverManager.getConnection("jdbc:mysql://localhost:3306/frs_project","root","1234");
	    System.out.println("Connected successfully...");
	    }
	    catch(ClassNotFoundException cnf)
	    {
	        System.out.println(cnf);
	    }
	    catch(SQLException sql)
	    {
	        System.out.println(sql);
	    }
		
		return con;
	}

}
