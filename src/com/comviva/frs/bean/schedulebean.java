package com.comviva.frs.bean;

public class schedulebean {

	private int travelduration;
	private String scheduleid,flightid,routeid,availabledays,departuretime;
	
	public int getTravelduration() {
		return travelduration;
	}
	public void setTravelduration(int travelduration) {
		this.travelduration = travelduration;
	}
	public String getScheduleid() {
		return scheduleid;
	}
	public void setScheduleid(String scheduleid) {
		this.scheduleid = scheduleid;
	}
	public String getFlightid() {
		return flightid;
	}
	public void setFlightid(String flightid) {
		this.flightid = flightid;
	}
	public String getRouteid() {
		return routeid;
	}
	public void setRouteid(String routeid) {
		this.routeid = routeid;
	}
	public String getAvailabledays() {
		return availabledays;
	}
	public void setAvailabledays(String availabledays) {
		this.availabledays = availabledays;
	}
	public String getDeparturetime() {
		return departuretime;
	}
	public void setDeparturetime(String departuretime) {
		this.departuretime = departuretime;
	}

}
