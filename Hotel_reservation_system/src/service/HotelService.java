package service;

import entity.Room;
import entity.Customer;
import entity.Reservation;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HotelService {
    private List<Room> rooms;
    private List<Customer> customers;
    private List<Reservation> reservations;

    public HotelService() {
        rooms = new ArrayList<>();
        customers = new ArrayList<>();
        reservations = new ArrayList<>();
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public List<Room> viewAvailableRooms() {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (room.isAvailable()) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    public void bookRoom(String roomId, String customerId, LocalDate checkInDate, LocalDate checkOutDate) throws Exception {
        Room room = findRoomById(roomId);
        Customer customer = findCustomerById(customerId);

        if (room == null) {
            throw new Exception("Room not found.");
        }
        if (customer == null) {
            throw new Exception("Customer not found.");
        }
        if (!room.isAvailable()) {
            throw new Exception("Room is not available.");
        }

        String reservationId = "R" + (reservations.size() + 1);
        Reservation reservation = new Reservation(reservationId, roomId, customerId, checkInDate, checkOutDate);
        reservations.add(reservation);
        room.setAvailability(false); // Mark room as unavailable
        generateInvoice(customer, reservation);
    }

    private Room findRoomById(String roomId) {
        for (Room room : rooms) {
            if (room.getRoomId().equals(roomId)) {
                return room;
            }
        }
        return null;
    }

    private Customer findCustomerById(String customerId) {
        for (Customer customer : customers) {
            if (customer.getCustomerId().equals(customerId)) {
                return customer;
            }
        }
        return null;
    }

    private void generateInvoice(Customer customer, Reservation reservation) {
        double totalCost = reservation.getTotalNights() * findRoomById(reservation.getRoomId()).getPricePerNight();

        String invoice = "Invoice ID: " + reservation.getReservationId() +
                "\nCustomer Name: " + customer.getCustomerName() +
                "\nRoom ID: " + reservation.getRoomId() +
                "\nCheck-In Date: " + reservation.getCheckInDate() +
                "\nCheck-Out Date: " + reservation.getCheckOutDate() +
                "\nTotal Nights: " + reservation.getTotalNights() +
                "\nTotal Cost: $" + totalCost;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("invoice_" + reservation.getReservationId() + ".txt"))) {
            writer.write(invoice);
            System.out.println("Invoice generated: invoice_" + reservation.getReservationId() + ".txt");
        } catch (IOException e) {
            System.out.println("Error generating invoice: " + e.getMessage());
        }
    }

    public List<Reservation> getCurrentReservations() {
        return reservations;
    }
}
