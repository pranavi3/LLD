package parking;

import parkingspot.ParkingSpot;
import vehicle.Vehicle;

import java.util.Date;
import java.util.UUID;

public class ParkingTicket {
    private final String ticketId;
    private final Vehicle vehicle;
    private final long entryTime;
    private long exitTime;
    private final ParkingSpot parkingSpot;

    public ParkingTicket(Vehicle vehicle,ParkingSpot parkingSpot) {
        this.ticketId = UUID.randomUUID().toString();;
        this.vehicle = vehicle;
        this.entryTime = new Date().getTime();
        this.parkingSpot = parkingSpot;
    }


    public String getTicketId() {
        return ticketId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public long getEntryTime() {
        return entryTime;
    }

    public long getExitTime() {
        return exitTime;
    }

    public void setExitTime() {
        this.exitTime = new Date().getTime();;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }
}
