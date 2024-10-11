package entity;

public class Room {
    private String roomId;
    private String roomType;
    private double pricePerNight;
    private boolean availability;

    public Room(String roomId, String roomType, double pricePerNight) {
        this.roomId = roomId;
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
        this.availability = true; // Rooms are available by default
    }

    public String getRoomId() { return roomId; }
    public String getRoomType() { return roomType; }
    public double getPricePerNight() { return pricePerNight; }
    public boolean isAvailable() { return availability; }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
}
