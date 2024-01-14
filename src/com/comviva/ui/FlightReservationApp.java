package com.comviva.ui;


import javax.swing.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import com.comviva.frs.bean.*;
import com.comviva.frs.service.*;
import com.comviva.frs.util.*;

public class FlightReservationApp {

    private static final Administrator adminService = new Administrator();
    private static final Customer customerService = new Customer();
    private static final User userService = new User();

    public static void main(String[] args) {
        while (true) {
            String[] options = {"Administrator", "Customer", "Exit"};
            int choice = JOptionPane.showOptionDialog(null, "Welcome to Flight Reservation System",
                    "Flight Reservation", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
                    options, options[0]);

            switch (choice) {
                case 0:
                    administratorLogin();
                    break;
                case 1:
                    customerLogin();
                    break;
                case 2:
                    JOptionPane.showMessageDialog(null, "Exiting Flight Reservation System. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid option. Please choose again.");
            }
        }
    }

    private static void administratorLogin() {
        JTextField userIdField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        Object[] message = {"UserID:", userIdField, "Password:", passwordField};

        int option = JOptionPane.showConfirmDialog(null, message, "Administrator Login", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String userId = userIdField.getText();
            String password = new String(passwordField.getPassword());

            credentialbean credentialsBean = new credentialbean(userId, password);
            String userType = userService.login(credentialsBean);

            if ("A".equals(userType)) {
                JOptionPane.showMessageDialog(null, "Administrator login successful.");
                administratorMenu();
            } else if ("C".equals(userType)) {
                JOptionPane.showMessageDialog(null, "You are not authorized to log in as an Administrator.");
            } else {
                JOptionPane.showMessageDialog(null, "Invalid credentials. Please try again.");
            }
        }
    }

    private static void administratorMenu() {
        while (true) {
            String[] adminOptions = {"Add Flight", "Modify Flight", "Remove Flight", "Add Schedule", "Modify Schedule",
                    "Remove Schedule", "Add Route", "Modify Route", "Remove Route", "View All Flights",
                    "View All Routes", "View All Schedules", "Logout"};
            int adminChoice = JOptionPane.showOptionDialog(null, "Administrator Menu", "Flight Reservation",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, adminOptions, adminOptions[0]);

            switch (adminChoice) {
                case 0:
                    // Implement Add Flight logic
                    break;
                case 1:
                    // Implement Modify Flight logic
                    break;
                case 2:
                    // Implement Remove Flight logic
                    break;
                case 3:
                    // Implement Add Schedule logic
                    break;
                case 4:
                    // Implement Modify Schedule logic
                    break;
                case 5:
                    // Implement Remove Schedule logic
                    break;
                case 6:
                    // Implement Add Route logic
                    break;
                case 7:
                    // Implement Modify Route logic
                    break;
                case 8:
                    // Implement Remove Route logic
                    break;
                case 9:
                    // Implement View All Flights logic
                    break;
                case 10:
                    // Implement View All Routes logic
                    break;
                case 11:
                    // Implement View All Schedules logic
                    break;
                case 12:
                    JOptionPane.showMessageDialog(null, "Logging out as Administrator.");
                    return;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid option. Please choose again.");
            }
        }
    }

    private static void customerLogin() {
        JTextField userIdField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        Object[] message = {"UserID:", userIdField, "Password:", passwordField};

        int option = JOptionPane.showConfirmDialog(null, message, "Customer Login", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String userId = userIdField.getText();
            String password = new String(passwordField.getPassword());

            credentialbean credentialsBean = new credentialbean(userId, password);
            String userType = userService.login(credentialsBean);

            if ("C".equals(userType)) {
                JOptionPane.showMessageDialog(null, "Customer login successful.");
                customerMenu();
            } else if ("A".equals(userType)) {
                JOptionPane.showMessageDialog(null, "You are not authorized to log in as a Customer.");
            } else {
                JOptionPane.showMessageDialog(null, "Invalid credentials. Please try again.");
            }
        }
    }

    private static void customerMenu() {
        while (true) {
            String[] customerOptions = {"View Schedule by Route", "Reserve Ticket", "Cancel Ticket", "View Ticket",
                    "Print Ticket", "Logout"};
            int customerChoice = JOptionPane.showOptionDialog(null, "Customer Menu", "Flight Reservation",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, customerOptions, customerOptions[0]);

            switch (customerChoice) {
                case 0:
                    // Implement View Schedule by Route logic
                    break;
                case 1:
                    // Implement Reserve Ticket logic
                    break;
                case 2:
                    // Implement Cancel Ticket logic
                    break;
                case 3:
                    // Implement View Ticket logic
                    break;
                case 4:
                    // Implement Print Ticket logic
                    break;
                case 5:
                    JOptionPane.showMessageDialog(null, "Logging out as Customer.");
                    return;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid option. Please choose again.");
            }
        }
    }
}
