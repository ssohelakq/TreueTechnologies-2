package parking;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ParkingSystem 
{
	private static Map<Integer, ParkingSpot> parkingSpots = new HashMap<>();
    private static Map<String, User> users = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeParkingSpots();
        boolean exit = false;

        while (!exit) {
            System.out.println("1. Register\n2. Login\n3. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    registerUser();
                    break;
                case 2:
                    loginUser();
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void initializeParkingSpots() {
        for (int i = 1; i <= 10; i++) {
            parkingSpots.put(i, new ParkingSpot(i));
        }
    }

    private static void registerUser() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        if (users.containsKey(username)) {
            System.out.println("Username already exists. Please choose a different username.");
            return;
        }

        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        users.put(username, new User(username, password));
        System.out.println("Registration successful!");
    }

    private static void loginUser() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        if (!users.containsKey(username)) {
            System.out.println("Username not found. Please register first.");
            return;
        }

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = users.get(username);

        if (!user.authenticate(password)) {
            System.out.println("Invalid password. Please try again.");
            return;
        }

        handleUserActions(user);
    }

    private static void handleUserActions(User user) {
        boolean logout = false;

        while (!logout) {
            System.out.println("\n1. Search for parking spot\n2. View bookings\n3. Logout");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    searchParkingSpot(user);
                    break;
                case 2:
                    viewBookings(user);
                    break;
                case 3:
                    logout = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void searchParkingSpot(User user) {
        System.out.println("Available parking spots:");
        for (ParkingSpot spot : parkingSpots.values()) {
            if (spot.isAvailable()) {
                System.out.println("Spot " + spot.getSpotId());
            }
        }

        System.out.print("Enter spot number to book: ");
        int spotNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        ParkingSpot selectedSpot = parkingSpots.get(spotNumber);
        if (selectedSpot == null || !selectedSpot.isAvailable()) {
            System.out.println("Invalid spot number or spot is already booked.");
            return;
        }

        selectedSpot.book();
        user.addBooking(selectedSpot);
        System.out.println("Booking confirmed for spot " + spotNumber);
    }

    private static void viewBookings(User user) {
        List<ParkingSpot> bookings = user.getBookings();
        if (bookings.isEmpty()) {
            System.out.println("No bookings found.");
            return;
        }

        System.out.println("Your bookings:");
        for (ParkingSpot spot : bookings) {
            System.out.println("Spot " + spot.getSpotId());
        }
    }
}

