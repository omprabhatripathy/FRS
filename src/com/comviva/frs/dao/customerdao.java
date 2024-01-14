package com.comviva.frs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.comviva.frs.bean.passengerbean;
import com.comviva.frs.bean.reservationbean;
import com.comviva.frs.bean.schedulebean;
import com.comviva.frs.service.Customer;
import com.comviva.frs.util.DBUtil;

public class customerdao implements Customer {
	public static Connection con = DBUtil.con;
	public static PreparedStatement ps;
	public static ResultSet rs;

	@Override
	public ArrayList<schedulebean> viewScheduleByRoute(String source, String destination, Date date) {
		// TODO Auto-generated method stub
		ArrayList<schedulebean> schedule_array = new ArrayList<>();
        schedulebean sb = new schedulebean();


	    try {
	        ps = con.prepareStatement("SELECT * FROM frs_tbl_schedule " +
	                                  "WHERE routeid IN (SELECT RouteId FROM frs_tbl_route " +
	                                  "WHERE source = ? AND destination = ?) " +
	                                  "AND ? BETWEEN DATE_ADD(departuretime, INTERVAL -1 DAY) " +
	                                  "AND DATE_ADD(departuretime, INTERVAL 1 DAY)");
	        ps.setString(1, source);
	        ps.setString(2, destination);
	        ps.setDate(3, new java.sql.Date(date.getTime())); 
	        rs = ps.executeQuery();

	        while (rs.next()) {
	            sb.setScheduleid(rs.getString("scheduleid"));
	            sb.setFlightid(rs.getString("flightid"));
	            sb.setRouteid(rs.getString("routeid"));
	            sb.setTravelduration(rs.getInt("travelduration"));
	            sb.setAvailabledays(rs.getString("availabledays"));
	            sb.setDeparturetime(rs.getString("departuretime"));

	            schedule_array.add(sb);
	        }

	    	} catch (SQLException e) {
	    	return schedule_array;
	    }

	    return schedule_array;
	}

	
	@Override
	public String reserveTicket(reservationbean reservationBean, ArrayList<passengerbean> passengers) {
		reservationbean rb = new reservationbean();
		int i =0;
	        try {
		    	ps = con.prepareStatement ( "INSERT INTO frs_tbl_reservation VALUES (?, ?, ?, ?)");
	            ps.setString(1, reservationBean.getReservationid());
	            ps.setString(2, reservationBean.getScheduleid());
	            ps.setString(3, reservationBean.getUserid());
	            ps.setDouble(4, reservationBean.getTotalfare());
	            i = ps.executeUpdate();
	        }
	        catch(SQLException sql)
	        {
	
	        }
	        try{
		        ps = con.prepareStatement ("INSERT INTO frs_tbl_passenger VALUES (?, ?, ?, ?, ?)");
	            for (passengerbean passenger : passengers) {
	                ps.setString(1, passenger.getReservationid());
	                ps.setString(2, passenger.getName());
	                ps.setString(3, passenger.getGender());
	                ps.setInt(4, passenger.getAge());
	                ps.setInt(5, passenger.getSeatno());
	                ps.executeUpdate();
	            }
	            return "success";

	        }
	        catch (SQLException e) {
	        	return e.toString();
	        }
		    }
	           
	         
	   

	
	@Override
	public boolean cancelTicket(String reservationId) {
	   
	        try{
		        ps= con.prepareStatement( "DELETE FROM frs_tbl_passenger WHERE reservationid = ?");
	            ps.setString(1, reservationId);
	            ps.executeUpdate();
	        }
	        catch (SQLException e) {
	        	return false;
	        }
		   
	        try {
		        ps= con.prepareStatement("DELETE FROM frs_tbl_reservation WHERE reservationid = ?");
	            ps.setString(1, reservationId);
	            int i = ps.executeUpdate();

	            if (i > 0){
	                return true;
	            } else {
	                return false;
	            }
	        
	        } catch (SQLException e) {
	        	return false;
	        }
	        }
	     
	

	
	@Override
	public Map<reservationbean, passengerbean> viewTicket(String reservationId) {
	    Map<reservationbean, passengerbean> ticketDetails = new HashMap<>();

	    try {
	        ps = con.prepareStatement("SELECT * FROM frs_tbl_reservation WHERE reservationid = ?");
	        ps.setString(1, reservationId);
	        rs = ps.executeQuery();

	        if (rs.next()) {
	            reservationbean rb = new reservationbean();
	            rb.setReservationid(rs.getString("reservationid"));
	            rb.setScheduleid(rs.getString("scheduleid"));
	            rb.setUserid(rs.getString("userid"));
	            rb.setTotalfare(rs.getDouble("totalfare"));

	            // Retrieve passenger details
	            ps = con.prepareStatement("SELECT * FROM frs_tbl_passenger WHERE reservationid = ?");
	            ps.setString(1, reservationId);
	            rs = ps.executeQuery();

	            if (rs.next()) {
	                passengerbean pb = new passengerbean();
	                pb.setReservationid(rs.getString("reservationid"));
	                pb.setName(rs.getString("name"));
	                pb.setGender(rs.getString("gender"));
	                pb.setAge(rs.getInt("age"));
	                pb.setSeatno(rs.getInt("seatno"));

	                ticketDetails.put(rb, pb);
	            }
	        }
	    } catch (SQLException e) {
	    	return null;
	    }

	    return ticketDetails;
	}

	@Override
	public Map<reservationbean, passengerbean> printTicket(String reservationId) {
		// TODO Auto-generated method stub
		return viewTicket(reservationId); 
	}

}
