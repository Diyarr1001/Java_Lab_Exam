package entity;

import java.time.LocalDate;

public class Reservation {
    private String reservationId;
    private String roomId;
    private String customerId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    public Reservation(String reservationId, String roomId, String customerId, LocalDate checkInDate, LocalDate checkOutDate) {
        this.reservationId = reservationId;
        this.roomId = roomId;
        this.customerId = customerId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public String getReservationId() { return reservationId; }
    public String getRoomId() { return roomId; }
    public String getCustomerId() { return customerId; }
    public LocalDate getCheckInDate() { return checkInDate; }
    public LocalDate getCheckOutDate() { return checkOutDate; }

    public long getTotalNights() {
        return checkOutDate.toEpochDay() - checkInDate.toEpochDay();
    }
}
