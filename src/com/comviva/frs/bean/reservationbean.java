package com.comviva.frs.bean;

import java.util.Date;

public class reservationbean {

	private Date bookingadte,journeydate;
	private int noofseats,bookingstatus;
	private double totalfare;	
	private String reservationid,userid,scheduleid,reservationtype;
	public Date getBookingadte() {
		return bookingadte;
	}
	public void setBookingadte(Date bookingadte) {
		this.bookingadte = bookingadte;
	}
	public Date getJourneydate() {
		return journeydate;
	}
	public void setJourneydate(Date journeydate) {
		this.journeydate = journeydate;
	}
	public int getNoofseats() {
		return noofseats;
	}
	public void setNoofseats(int noofseats) {
		this.noofseats = noofseats;
	}
	public int getBookingstatus() {
		return bookingstatus;
	}
	public void setBookingstatus(int bookingstatus) {
		this.bookingstatus = bookingstatus;
	}
	public double getTotalfare() {
		return totalfare;
	}
	public void setTotalfare(double totalfare) {
		this.totalfare = totalfare;
	}
	public String getReservationid() {
		return reservationid;
	}
	public void setReservationid(String reservationid) {
		this.reservationid = reservationid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getScheduleid() {
		return scheduleid;
	}
	public void setScheduleid(String scheduleid) {
		this.scheduleid = scheduleid;
	}
	public String getReservationtype() {
		return reservationtype;
	}
	public void setReservationtype(String reservationtype) {
		this.reservationtype = reservationtype;
	}

	
	
}
