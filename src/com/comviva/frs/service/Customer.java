package com.comviva.frs.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import com.comviva.frs.bean.passengerbean;
import com.comviva.frs.bean.reservationbean;
import com.comviva.frs.bean.schedulebean;

public interface Customer {
	public ArrayList<schedulebean> viewScheduleByRoute(String source, String destination, Date date);
	public String reserveTicket(reservationbean reservationBean, ArrayList<passengerbean> passengers);
	public boolean cancelTicket(String reservationId);
	public Map<reservationbean,passengerbean> viewTicket(String reservationId);
	public Map<reservationbean,passengerbean> printTicket(String reservationId);
}
