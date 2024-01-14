package com.comviva.frs.util;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.comviva.frs.bean.credentialbean;
import com.comviva.frs.bean.profilebean;
import com.comviva.frs.util.Authentication;
import com.comviva.frs.util.DBUtil;
import com.comviva.frs.util.User;

public class User {
	
	public static Connection con  = DBUtil.con;
	public static PreparedStatement ps;
	public static ResultSet rs;
	
	public String login(credentialbean credentialbean){
		int counter = 0;
		try {
			ps = con.prepareStatement("Select * from frs_tbl_user_credentials where userid = ? and password  = ?");
			ps.setString(1, credentialbean.getUserid());
			ps.setString(2, credentialbean.getPassword());
			rs = ps.executeQuery();
			counter  = ps.executeUpdate();
			if (counter == 1) {
				Authentication.authenticate(credentialbean);
				Authentication.changeLoginStatus(credentialbean,1);
				ps = con.prepareStatement("Update frs_tbl_user_credentials set loginstatus = 1 where userid = ?");
				ps.setString(1, credentialbean.getUserid());
				if (credentialbean.getUserid().startsWith("A")) {
					return "A";
				}
				else {
					return "C";
				}
			}
			else {
				return  "INVALID";
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return "FAIL";
		}
	}
	public boolean logout(String userId) {
		int counter = 0;
		try {
			ps = con.prepareStatement("update frs_tbl_user_credentials set loginstatus = 0 where userid = ? ");
			ps.setString(1, userId);
			counter = ps.executeUpdate();
			if (counter == 1) {
				return true;
			}
			else {
				return false;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return false;
		}	
	}
	public String changePassword(credentialbean credentialsBean, String newPassword) {
		int counter = 0;
		try {
			ps = con.prepareStatement("update frs_tbl_user_credentials set password = ? where userid =  ?");
			ps.setString(1, newPassword);
			ps.setString(1, credentialsBean.getUserid());
			if (counter == 1) {
				return "SUCCESS";
			}
			else {
				return "FAIL";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return "INVALID";
		}
		
	}
	public String register(profilebean profileBean) {
		int counter = 0;
		if (profileBean.getUserid().length() <= 6 ) {
			return "FAIL";
		}
		else {
			try {
				ps = con.prepareStatement("insert into frs_tbl_user_profile values (?,?,?,?,?,?,?,?,?,?,?,?)");
				ps.setString(1, profileBean.getUserid());
				ps.setString(2, profileBean.getFirstname());
				ps.setString(3, profileBean.getFirstname());
				ps.setDate(4,  JavaDateConverter(profileBean.getDateofbirth()));
				ps.setString(5, profileBean.getGender());
				ps.setString(6, profileBean.getStreet());
				ps.setString(7, profileBean.getLocation());
				ps.setString(8, profileBean.getCity());
				ps.setString(9, profileBean.getState());
				ps.setString(10, profileBean.getPincode());
				ps.setString(11, profileBean.getMobileno());
				ps.setString(12, profileBean.getEmailid());
				rs = ps.executeQuery();
				counter = ps.executeUpdate();
				if (counter == 1) {
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return null;
		
	}
	public Date JavaDateConverter(java.util.Date dateOfBirth) {
		// TODO Auto-generated method stub
		java.sql.Date sql_date=(new java.sql.Date(dateOfBirth.getTime()));
		return sql_date;
	}
}
