package com.comviva.frs.util;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.comviva.frs.util.DBUtil;
import com.comviva.frs.bean.FlightBean;
import com.comviva.frs.bean.credentialbean;
import com.comviva.frs.dao.AdminstratorDAO;
import com.comviva.frs.dao.administratordao;
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


public class Authentication {
	
	public static Connection con  = DBUtil.con;
	public static PreparedStatement ps;
	public static boolean flag;
	public static ResultSet rs;
	
	public static boolean authenticate(credentialbean credentialsBean) {
		flag =false;
		System.out.println("Doing Authentication");
		try {
			ps = con.prepareStatement("Select * from FRS_TBL_User_Credentials where Userid =? and Password  =?");
			ps.setString(1, credentialsBean.getUserID());
			ps.setString(2, credentialsBean.getPassword());
			rs = ps.executeQuery();
			rs.next();
//			System.out.println("authenticating done");
			if (rs.getString(1)!= null) {
				System.out.println("in try if");
				flag = true;
			}
			else {
				System.out.println("in try else");
				flag =false;
			}
			return flag;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("in catch");
			return flag ;
		}
	}
	public String authorize(String operation) {
		switch(operation) {
		case "AD-001":
				boolean for_loop = true;
				JOptionPane.showMessageDialog(null, "Your work is to Add Flight");
				while(for_loop == true) {
					FlightBean fb = new FlightBean();
					String flightid = JOptionPane.showInputDialog("Enter the FlightID");
					String flightname = JOptionPane.showInputDialog("Enter the Flight Name");
					int seating_capacity = Integer.parseInt(JOptionPane.showInputDialog("Enter the Seating Capacity"));
					int reservation_capacity = Integer.parseInt(JOptionPane.showInputDialog("Enter the Reservation Capacity"));
					fb.setFlightID(flightid);
					fb.setFlightName(flightname);
					fb.setSeatingCapacity(seating_capacity);
					fb.setReservationCapacity(reservation_capacity);
					AdminstratorDAO dao = new AdminstratorDAO();
					String a = dao.addFlight(fb);
					if (a == "SUCCESS") {
						JOptionPane.showMessageDialog(null, "Congratulations your flight is added");
					}
					else {
						JOptionPane.showMessageDialog(null, "Your Flight is not added");
					}
					
					int return_value = Integer.parseInt(JOptionPane.showInputDialog("Select from the following"+" "+ "\n"
							+ "1: If You want to add  Flight"
							+ "\n"
							+ "2: If you want to exit"));
					if (return_value != 1) {
							for_loop = false;
							JOptionPane.showMessageDialog(null, "Your work is done ..:)");
						}
					
				}
				break;
			case "AD-002":
				for_loop = true;
				JOptionPane.showMessageDialog(null, "Your work is to delete flight");
				int input_value = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of flight you want to be deleted "));
				ArrayList<String> afb = new ArrayList<String>();
				for (int i=0;i<input_value;i++) {
					String fid = JOptionPane.showInputDialog("Enter the  flight id");
					afb.add(fid);
				}
				administratordao dao = new administratordao();
				int a = dao.removeFlight(afb);
				
				if (a == 1) {
					JOptionPane.showMessageDialog(null, "Your FLight id is deleted");
				}
				else {
					JOptionPane.showMessageDialog(null, "Congratulations your "+a+" flights are deleted");
				}
				break;
				
			case "AD-003" :
				JOptionPane.showMessageDialog(null, "Your work is to view flight");
				for_loop = true;
				while(for_loop == true) {
					FlightBean fb = new FlightBean();
					String flightid = JOptionPane.showInputDialog("Enter the FlightID");
					String flightname = JOptionPane.showInputDialog("Enter the Flight Name");
					int seating_capacity = Integer.parseInt(JOptionPane.showInputDialog("Enter the Seating Capacity"));
					int reservation_capacity = Integer.parseInt(JOptionPane.showInputDialog("Enter the Reservation Capacity"));
					fb.setFlightID(flightid);
					fb.setFlightName(flightname);
					fb.setSeatingCapacity(seating_capacity);
					fb.setReservationCapacity(reservation_capacity);
					AdminstratorDAO dao = new AdminstratorDAO();
					String a = dao.modifyFlight(fb);

				case "AD-003" :
					JOptionPane.showMessageDialog(null, "Your work is to view flight");
					for_loop = true;
					while(for_loop == true) {
						FlightBean fb = new FlightBean();
						String flightid = JOptionPane.showInputDialog("Enter the FlightID");
						String flightname = JOptionPane.showInputDialog("Enter the Flight Name");
						int seating_capacity = Integer.parseInt(JOptionPane.showInputDialog("Enter the Seating Capacity"));
						int reservation_capacity = Integer.parseInt(JOptionPane.showInputDialog("Enter the Reservation Capacity"));
						fb.setFlightID(flightid);
						fb.setFlightName(flightname);
						fb.setSeatingCapacity(seating_capacity);
						fb.setReservationCapacity(reservation_capacity);
						AdminstratorDAO dao = new AdminstratorDAO();
						String a = dao.viewByFlightId(fb);
						if (a == "SUCCESS") {
							JOptionPane.showMessageDialog(null, "Here are your flight details : "+ "Flight ID :" +flightid + "Flight Name: "+ flightname + " with seating capacity : "+seating_capacity+ reservation_capacity +" seats has been reserved !" );
						}
						else {
							JOptionPane.showMessageDialog(null,"");
							for_loop==false;
						}
					case "US-001":
						JOptionPane.showMessageDialog(null, "Your work is to add user profile");
						for_loop = true;
						while(for_loop =true) {
							String fname = JOptionPane.showInputDialog("Enter the First Name");
							String lname  = JOptionPane.showInputDialog("Enter the last Name");
//							Date dob = dateFormat.parse(JOptionPane.showInputDialog("Enter the Date of birth"));
							String Gender = JOptionPane.showInputDialog("Enter the Gender");
							String Street = JOptionPane.showInputDialog("Enter the Street");
							String location = JOptionPane.showInputDialog("Enter the Location");
							String City = JOptionPane.showInputDialog("Enter the City");
							String State = JOptionPane.showInputDialog("Enter the State");
							String Pincode = JOptionPane.showInputDialog("Enter the Pincode");
							String Mobilenum = JOptionPane.showInputDialog("Enter the Mobile Number");
							String email = JOptionPane.showInputDialog("Enter the Email"); 
							ProfileBean pb = new ProfileBean();
							pb.setFirstName(fname);
							pb.setLastName(lname);
//							pb.setDateOfBirth();
							pb.setGender(Gender);
							pb.setStreet(Street);
							pb.setLocation(location);
							pb.setCity(City);
							pb.setState(State);
							pb.setPincode(Pincode);
							pb.setMobileNo(Mobilenum);
							pb.setEmailID(email);
							User user = new User();
							return_value_string = user.register(pb);
							if (return_value_string == "SUCCESS") {
								JOptionPane.showMessageDialog(null, "Congratulations on Registratration ...:)");
							}
							else {
								JOptionPane.showMessageDialog(null, "Sorry for the inconvinience...:(");
								return_value_int = Integer.parseInt(JOptionPane.showInputDialog("Select from the following" + " " + "\n"
						                + "1: If You want to do another Registration"
						                + "\n"
						                + "2: If you want to exit"));
		 
						        if (return_value_int != 1) {
						            for_loop= false;
						            JOptionPane.showMessageDialog(null, "Your work is done ..:)");
						        }
							}
						}

				}		
				
					
				case "US-003":
				    JOptionPane.showMessageDialog(null, "Your work is to Reserve Ticket");
				    for_loop = true;
				    while (for_loop) {
				        List<ScheduleBean> availableSchedules = getAvailableSchedules();
				        
				        if (availableSchedules.isEmpty()) {
				            JOptionPane.showMessageDialog(null, "No available schedules for reservation.");
				            break;
				        }
				        StringBuilder scheduleList = new StringBuilder("Available Schedules:\n");
				        for (ScheduleBean schedule : availableSchedules) {
				            scheduleList.append(schedule.toString()).append("\n");
				        }
				        JOptionPane.showMessageDialog(null, scheduleList.toString());
				        String scheduleId = JOptionPane.showInputDialog("Enter the Schedule ID to reserve a ticket");
				        ScheduleBean selectedSchedule = getScheduleDetails(scheduleId);
				        if (selectedSchedule != null) {
				            int numPassengers = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of passengers"));
				            List<PassengerBean> passengers = new ArrayList<>();
				            for (int i = 0; i < numPassengers; i++) {
				                String passengerName = JOptionPane.showInputDialog("Enter Passenger Name");
				                int age = Integer.parseInt(JOptionPane.showInputDialog("Enter Age"));
				                String gender = JOptionPane.showInputDialog("Enter Gender");
				                PassengerBean passenger = new PassengerBean();
				                passenger.setPassengerName(passengerName);
				                passenger.setAge(age);
				                passenger.setGender(gender);
				                passengers.add(passenger);
				            }
				            if (!reservationId.isEmpty()) {
				                JOptionPane.showMessageDialog(null, "Reservation successful! Reservation ID: " + reservationId);
				            } else {
				                JOptionPane.showMessageDialog(null, "Failed to reserve ticket.");
				            }
				        }
				        return_value_int = Integer.parseInt(JOptionPane.showInputDialog("Select from the following" + " " + "\n"
				                + "1: If You want to reserve another ticket"
				                + "\n"
				                + "2: If you want to exit"));
				        if (return_value_int != 1) {
				            for_loop = false;
				            JOptionPane.showMessageDialog(null, "Your work is done ..:)");
				        }
				    }
				    break;
				 
				[1:45 AM] Omprabha Tripathy
				final 04
				case "US-004":
				    JOptionPane.showMessageDialog(null, "Your work is to Cancel Ticket");
				    for_loop = true;
				    while (for_loop) {
				        Map<ReservationBean, PassengerBean> userReservations = getUserReservations(userId);
				        if (userReservations.isEmpty()) {
				            JOptionPane.showMessageDialog(null, "No reservations found for the user.");
				            break;
				        }
				        StringBuilder reservationList = new StringBuilder("Your Reservations:\n");
				        for (Map.Entry<ReservationBean, PassengerBean> entry : userReservations.entrySet()) {
				            reservationList.append("Reservation ID: ").append(entry.getKey().getReservationId())
				                    .append(", Schedule ID: ").append(entry.getKey().getScheduleId())
				                    .append(", Passenger: ").append(entry.getValue().getPassengerName())
				                    .append("\n");
				        }
				        JOptionPane.showMessageDialog(null, reservationList.toString());
				        String reservationIdToCancel = JOptionPane.showInputDialog("Enter the Reservation ID to cancel");
				        ReservationBean selectedReservation = getReservationDetails(reservationIdToCancel);
				        if (selectedReservation != null) {
				            if (isCancellationAllowed(selectedReservation.getScheduledDate())) {
				                CustomerDAO customerDAO = new CustomerDAO(); 
				                boolean isCancelled = customerDAO.cancelTicket(reservationIdToCancel);
				                if (isCancelled) {
				                    JOptionPane.showMessageDialog(null, "Ticket cancellation successful for Reservation ID: " + reservationIdToCancel);
				                } else {
				                    JOptionPane.showMessageDialog(null, "Failed to cancel ticket.");
				                }
				            } else {
				                JOptionPane.showMessageDialog(null, "Ticket cancellation is not allowed as the scheduled date has passed.");
				            }
				        }
				        return_value_int = Integer.parseInt(JOptionPane.showInputDialog("Select from the following" + " " + "\n"
				                + "1: If You want to cancel another ticket"
				                + "\n"
				                + "2: If you want to exit"));
				        if (return_value_int != 1) {
				            for_loop = false;
				            JOptionPane.showMessageDialog(null, "Your work is done ..:)");
				        }
				    }
				    break;
				 
				[1:45 AM] Omprabha Tripathy
				case "US-005":
				    JOptionPane.showMessageDialog(null, "Your work is to View Ticket");
				    for_loop = true;
				    while (for_loop) {
				        Map<ReservationBean, PassengerBean> userReservations = getUserReservations(userId);
				        if (userReservations.isEmpty()) {
				            JOptionPane.showMessageDialog(null, "No reservations found for the user.");
				            break;
				        }
				        StringBuilder reservationList = new StringBuilder("Your Reservations:\n");
				        for (Map.Entry<ReservationBean, PassengerBean> entry : userReservations.entrySet()) {
				            reservationList.append("Reservation ID: ").append(entry.getKey().getReservationId())
				                    .append(", Schedule ID: ").append(entry.getKey().getScheduleId())
				                    .append(", Passenger: ").append(entry.getValue().getPassengerName())
				                    .append("\n");
				        }
				        JOptionPane.showMessageDialog(null, reservationList.toString());
				        String reservationIdToView = JOptionPane.showInputDialog("Enter the Reservation ID to view the ticket");
				        ReservationBean selectedReservation = getReservationDetails(reservationIdToView);
				        if (selectedReservation != null) {
				            Map<ReservationBean, PassengerBean> ticketDetails = getTicketDetails(reservationIdToView);
				            if (!ticketDetails.isEmpty()) {
				                StringBuilder ticketInfo = new StringBuilder("Ticket Details:\n");
				                for (Map.Entry<ReservationBean, PassengerBean> entry : ticketDetails.entrySet()) {
				                    ticketInfo.append("Reservation ID: ").append(entry.getKey().getReservationId())
				                            .append(", Schedule ID: ").append(entry.getKey().getScheduleId())
				                            .append(", Passenger: ").append(entry.getValue().getPassengerName())
				                            .append(", Seat: ").append(entry.getValue().getSeatNumber())
				                            .append("\n");
				                }
				                JOptionPane.showMessageDialog(null, ticketInfo.toString());
				            } else {
				                JOptionPane.showMessageDialog(null, "Ticket details not found for Reservation ID: " + reservationIdToView);
				            }
				        }
				        return_value_int = Integer.parseInt(JOptionPane.showInputDialog("Select from the following" + " " + "\n"
				                + "1: If You want to view another ticket"
				                + "\n"
				                + "2: If you want to exit"));
				        if (return_value_int != 1) {
				            for_loop = false;
				            JOptionPane.showMessageDialog(null, "Your work is done ..:)");
				        }
				    }
				    break;
				 
				[1:45 AM] Omprabha Tripathy
				case "US-006":
				    JOptionPane.showMessageDialog(null, "Your work is to Print Ticket");
				    for_loop = true;
				    while (for_loop) {
				        Map<ReservationBean, PassengerBean> userReservations = getUserReservations(userId);
				        if (userReservations.isEmpty()) {
				            JOptionPane.showMessageDialog(null, "No reservations found for the user.");
				            break;
				        }
				        StringBuilder reservationList = new StringBuilder("Your Reservations:\n");
				        for (Map.Entry<ReservationBean, PassengerBean> entry : userReservations.entrySet()) {
				            reservationList.append("Reservation ID: ").append(entry.getKey().getReservationId())
				                    .append(", Schedule ID: ").append(entry.getKey().getScheduleId())
				                    .append(", Passenger: ").append(entry.getValue().getPassengerName())
				                    .append("\n");
				        }
				        JOptionPane.showMessageDialog(null, reservationList.toString());
				        String reservationIdToPrint = JOptionPane.showInputDialog("Enter the Reservation ID to print the ticket");
				        ReservationBean selectedReservation = getReservationDetails(reservationIdToPrint);
				        if (selectedReservation != null) {
				            Map<ReservationBean, PassengerBean> ticketDetails = getTicketDetails(reservationIdToPrint);
				            if (!ticketDetails.isEmpty()) {
				                StringBuilder ticketInfo = new StringBuilder("Ticket Details:\n");
				                for (Map.Entry<ReservationBean, PassengerBean> entry : ticketDetails.entrySet()) {
				                    ticketInfo.append("Reservation ID: ").append(entry.getKey().getReservationId())
				                            .append(", Schedule ID: ").append(entry.getKey().getScheduleId())
				                            .append(", Passenger: ").append(entry.getValue().getPassengerName())
				                            .append(", Seat: ").append(entry.getValue().getSeatNumber())
				                            .append("\n");
				                }
				                JOptionPane.showMessageDialog(null, ticketInfo.toString());
				                JOptionPane.showMessageDialog(null, "Ticket printed successfully!");
				            } else {
				                JOptionPane.showMessageDialog(null, "Ticket details not found for Reservation ID: " + reservationIdToPrint);
				            }
				        }
				        return_value_int = Integer.parseInt(JOptionPane.showInputDialog("Select from the following" + " " + "\n"
				                + "1: If You want to print another ticket"
				                + "\n"
				                + "2: If you want to exit"));
				        if (return_value_int != 1) {
				            for_loop = false;
				            JOptionPane.showMessageDialog(null, "Your work is done ..:)");
				        }
				    }
				    break;
				 
			}
		}
			
		return "Your work is done :)";
	}

    
	public static boolean changeLoginStatus(credentialbean credentialsBean, int loginStatus) {
		try {
			ps = con.prepareStatement("Update frs_tbl_user_credentials set loginstatus = 1 where userid = ?");
			ps.setString(1, credentialsBean.getUserid());
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return false;
		}
	}
}
