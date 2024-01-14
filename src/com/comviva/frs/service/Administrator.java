package com.comviva.frs.service;

import java.util.ArrayList;
import com.comviva.frs.bean.flightbean;
import com.comviva.frs.bean.passengerbean;
import com.comviva.frs.bean.routebean;
import com.comviva.frs.bean.schedulebean;

public interface Administrator {
	public String addflight(flightbean flightbean);
	public boolean modifyFlight(flightbean flightBean);
	public int removeFlight(ArrayList<String> flightid);
	public String addSchedule(schedulebean scheduleBean);
	public boolean modifySchedule(schedulebean scheduleBean);
	public int removeschedule(ArrayList<String> scheduleId);
	public String addRoute(routebean routeBean);
	public boolean modifyRoute(routebean routeBean);
	public int removeRoute(ArrayList<String> routeid);
	public flightbean viewByflightId(String flightid);
	public routebean viewByRouteId(String routeid);
	public ArrayList<flightbean> viewByAllFlights();
	public ArrayList<routebean> viewByAllRoute();
	public ArrayList<schedulebean> viewByAllSchedule();
	public schedulebean viewByScheduleId(String scheduleId);
	public ArrayList<passengerbean> viewPassengersByFlight(String scheduleId);


}
