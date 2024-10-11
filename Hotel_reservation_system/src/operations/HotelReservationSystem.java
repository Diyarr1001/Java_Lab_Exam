package operations;

import entity.Room;
import entity.Customer;
import entity.Reservation;
import service.HotelService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class HotelReservationSystem {
    public static void main(String[] args) {
        HotelService hotelService = new HotelService();
        Scanner scanner = new Scanner(System.in);

        // Adding sample rooms
        hotelService.addRoom(new Room("101", "Single", 100.0));
        hotelService.addRoom(new Room("102", "Double", 150.0));
        hotelService.addRoom(new Room("103", "Suite", 250.0));

        // Adding sample customers
        hotelService.addCustomer(new Customer("C001", "Alice", "987654432"));
        hotelService.addCustomer(new Customer("C002", "Bob", "34242433"));

        while (true) {
            System.out.println("\n=== Hotel Reservation System ===");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Book Room");
            System.out.println("3. View Current Reservations");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    List<Room> availableRooms = hotelService.viewAvailableRooms();
                    System.out.println("Available Rooms:");
                    for (Room room : availableRooms) {
                        System.out.println(room);
                    }
                    break;

                case 2:
                    System.out.print("Enter Room ID: ");
                    String roomId = scanner.nextLine();
                    System.out.print("Enter Customer ID: ");
                    String customerId = scanner.nextLine();
                    System.out.print("Enter Check-In Date (YYYY-MM-DD): ");
                    LocalDate checkInDate = LocalDate.parse(scanner.nextLine());
                    System.out.print("Enter Check-Out Date (YYYY-MM-DD): ");
                    LocalDate checkOutDate = LocalDate.parse(scanner.nextLine());

                    try {
                        hotelService.bookRoom(roomId, customerId, checkInDate, checkOutDate);
                        System.out.println("Room booked successfully!");
                    } catch (Exception e) {
                        System.out.println("Booking failed: " + e.getMessage());
                    }
                    break;

                case 3:
                    List<Reservation> reservations = hotelService.getCurrentReservations();
                    System.out.println("Current Reservations:");
                    for (Reservation reservation : reservations) {
                        System.out.println("Reservation ID: " + reservation.getReservationId() +
                                ", Room ID: " + reservation.getRoomId() +
                                ", Customer ID: " + reservation.getCustomerId() +
                                ", Check-In: " + reservation.getCheckInDate() +
                                ", Check-Out: " + reservation.getCheckOutDate());
                    }
                    break;

                case 4:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
           
