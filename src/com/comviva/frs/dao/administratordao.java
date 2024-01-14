package com.comviva.frs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.comviva.frs.bean.*;
import com.comviva.frs.service.Administrator;
import com.comviva.frs.util.DBUtil;


public class administratordao implements Administrator{
	public static Connection con = DBUtil.con;
	public static PreparedStatement ps;
	public static ResultSet rs;


	@Override
	public String addflight(flightbean flightbean) {
		// TODO Auto-generated method stub
		int i=0;    

	    try
	    {
	    ps=con.prepareStatement("insert into frs_tbl_flight values(?,?,?,?)");
	    ps.setString(1,flightbean.getFlightid());
	    ps.setString(2, flightbean.getFlightname());
	    ps.setInt(3, flightbean.getSeatingcapacity());
	    ps.setInt(4,flightbean.getReservationcapacity()); 
	    
	     i=ps.executeUpdate();
	    }
	    catch(SQLException sql)
	    {
	    	return "ERROR";
	    }
	    if(i==1) {
	    	return "SUCCESS";
	    }else {
			return "FAIL";

	    }
		
	
	}
	
	
	//incomplete
	@Override
	public boolean modifyFlight(flightbean flightBean) {
		// TODO Auto-generated method stub
		boolean i = true;
		try {
			ps=con.prepareStatement("update frs_tbl_flight set ? = ? where flightid =?");
			return i;
		}catch(SQLException e){
			return i=false;
		}
		
	}

	@Override
	public int removeFlight(ArrayList<String> flightid) {
		// TODO Auto-generated method stub
		int i =0;
		for(String s :flightid) {
			try {
				ps= con.prepareStatement("delete from frs_tbl_flight where flightid = ?");
				ps.setString(1, s);
				i += ps.executeUpdate();
			}catch(SQLException e) {
				
			}
		
		}
		return i;
	}
	
	@Override
	public String addSchedule(schedulebean schedulebean) {
		// TODO Auto-generated method stub
		int i=0;    

	    try
	    {
	    ps=con.prepareStatement("insert into frs_tbl_schedule values(?,?,?,?,?,?)");
	    ps.setString(1,schedulebean.getScheduleid());
	    ps.setString(2, schedulebean.getFlightid());
	    ps.setString(3, schedulebean.getRouteid());
	    ps.setInt(4,schedulebean.getTravelduration()); 
	    ps.setString(5,schedulebean.getAvailabledays());
	    ps.setString(6,schedulebean.getDeparturetime());
	    
	     i=ps.executeUpdate();
	    }
	    catch(SQLException sql)
	    {
	    	return "ERROR";
	    }
	    if(i==1) {
	    	return "SUCCESS";
	    }else {
			return "FAIL";

	    }
	}


	@Override
	public boolean modifySchedule(schedulebean scheduleBean) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public int removeschedule(ArrayList<String> scheduleId) {
		// TODO Auto-generated method stub
		int i =0;
		for(String s : scheduleId) {
			try {
				ps = con.prepareStatement("delete from frs_tbl_schedule where scheduleid = ?");
				ps.setString(1, s);
				i += ps.executeUpdate();
			}catch(SQLException e){
				
			}
		}
		
		return i;
	}

	@Override
	public String addRoute(routebean routeBean) {
		// TODO Auto-generated method stub
		int counter = 0;
		try {
			ps = con.prepareStatement("insert into FRS_TBL_Route values(?,?,?,?,?)");
			ps.setString(1, routeBean.getRouteid());
			ps.setString(2, routeBean.getSource());
			ps.setString(3, routeBean.getDestination());
			ps.setInt(4, routeBean.getDistance());
			ps.setDouble(5, routeBean.getFare()); //Sql datatype int but in bean its double
			counter += ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return "ERROR";
		}
		if (counter == 1) {
			return "SUCCESS";
		}
		else {
			return "FAIL";
		}
	}

	@Override
	public boolean modifyRoute(routebean routeBean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int removeRoute(ArrayList<String> routeid) {
		// TODO Auto-generated method stub
		int i =0;
		for(String s : routeid) {
			try {
				ps = con.prepareStatement("delete from frs_tbl_route where RouteId = ?");
				ps.setString(1, s);
				i += ps.executeUpdate();
			}catch(SQLException e){
				
			}
		}
		return i;
	}

	@Override
	public flightbean viewByflightId(String flightid) {
		// TODO Auto-generated method stub
		flightbean fb = new flightbean();
		try {
			ps = con.prepareStatement("select* from frs_tbl_flight where flightid=?");
			ps.setString(1, flightid);
			rs = ps.executeQuery();
			fb.setFlightid(rs.getString(1));
			fb.setFlightname(rs.getString(2));
			fb.setSeatingcapacity(rs.getInt(3));
			fb.setReservationcapacity(rs.getInt(4));
		}catch(SQLException e){
			return fb;
			
		}
		
		return null;
	}

	@Override
	public routebean viewByRouteId(String routeid) {
		// TODO Auto-generated method stub
		routebean rb = new routebean();
		try {
			ps = con.prepareStatement("Select * from FRS_TBL_Route where RouteID = ?");
			ps.setString(1, routeid);
			rs = ps.executeQuery();
			rb.setRouteid(rs.getString(1));
			rb.setSource(rs.getString(2));
			rb.setDestination(rs.getString(3));
			rb.setDistance(rs.getInt(4));
			rb.setFare(rs.getDouble(5));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return rb;
		}
		return rb;
	}

	@Override
	public ArrayList<flightbean> viewByAllFlights() {
		// TODO Auto-generated method stub
		ArrayList<flightbean> array_fb = new ArrayList<flightbean>();
		try {
			ps = con.prepareStatement("select * from FRS_TBL_Flight ");
			rs = ps.executeQuery();
			while(rs.next()) {
				flightbean fb = new flightbean();
				fb.setFlightid(rs.getString(1));
				fb.setFlightname(rs.getString(2));
				fb.setSeatingcapacity(rs.getInt(3));
				fb.setReservationcapacity(rs.getInt(4));
				array_fb.add(fb);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return array_fb;
		}
		return array_fb;
	}

	@Override
	public ArrayList<routebean> viewByAllRoute() {
		// TODO Auto-generated method stub
		ArrayList<routebean> array_rb = new ArrayList<]routebean>();
		try {
			ps = con.prepareStatement("select * from FRS_TBL_Route");
			rs = ps.executeQuery();
			while(rs.next()) {
				routebean rb = new routebean();
				rb.setRouteid(rs.getString(1));
				rb.setSource(rs.getString(2));
				rb.setDestination(rs.getString(3));
				rb.setDistance(rs.getInt(4));
				rb.setFare(rs.getDouble(5));
				array_rb.add(rb);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return array_rb;
		}
		return array_rb;
	}

	@Override
	public ArrayList<schedulebean> viewByAllSchedule() {
		// TODO Auto-generated method stub
		ArrayList<schedulebean> array_sb = new ArrayList<schedulebean>();
		try {
			ps = con.prepareStatement("select * from FRS_TBL_Schedule");
			rs= ps.executeQuery();
			while(rs.next()) {
				schedulebean sb = new schedulebean();
				sb.setScheduleid(rs.getString(1));
				sb.setFlightid(rs.getString(2));
				sb.setRouteid(rs.getString(3));
				sb.setTravelduration(rs.getInt(4));
				sb.setDeparturetime(rs.getString(5));
				sb.setTravelduration(rs.getInt(6));
				array_sb.add(sb);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return array_sb;
		}
		return array_sb;
	}

	@Override
	public schedulebean viewByScheduleId(String scheduleId) {
		// TODO Auto-generated method stub
		schedulebean sb = new schedulebean();
		try {
			ps = con.prepareStatement("select * from FRS_TBL_Schedule where ScheduleId = ?");
			rs = ps.executeQuery();
			sb.setScheduleid(rs.getString(1));
			sb.setFlightid(rs.getString(2));
			sb.setRouteid(rs.getString(3));
			sb.setTravelduration(rs.getInt(4));
			sb.setDeparturetime(rs.getString(5));
			sb.setTravelduration(rs.getInt(6));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return sb;
		}
		return sb;
	
	}

	@Override
	public ArrayList<passengerbean> viewPassengersByFlight(String scheduleId) {
		// TODO Auto-generated method stub
		ArrayList<passengerbean> array_pb = new ArrayList<passengerbean>();
		try {
			ps = con.prepareStatement("Select p.Reservationid , p.Name , p.Gender "
					+ " p.Age ,p.Seatno"
					+ "from FRS_TBL_Passenger as p "
					+ "inner join "
					+ "FRS_TBL_Reservation as r "
					+ " on "
					+ "p.Reservationid = r.Reservationid "
					+ "where r.Scheduleid = ?");
			ps.setString(1, scheduleId);
			rs = ps.executeQuery();
			while (rs.next()) {
				passengerbean pb = new passengerbean();
				pb.setReservationid(rs.getString(1));
				pb.setName(rs.getString(2));
				pb.setGender(rs.getString(3));
				pb.setAge(rs.getInt(4));
				pb.setSeatno(rs.getInt(5));
				array_pb.add(pb);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return array_pb;
		}
		return array_pb;
	
	}


	
	
}
