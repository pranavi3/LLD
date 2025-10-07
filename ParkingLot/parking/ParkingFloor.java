package parking;

import parkingspot.ParkingSpot;
import vehicle.Vehicle;

import java.util.List;

public class ParkingFloor {
    private final int floorNumber;
    private final List<ParkingSpot> parkingSpotList;

    public ParkingFloor(int floorNumber, List<ParkingSpot> parkingSpotList) {
        this.floorNumber = floorNumber;
        this.parkingSpotList = parkingSpotList;
    }

    public synchronized ParkingSpot getAvailableSpot(Vehicle vehicle){
        for(ParkingSpot parkingSpot: parkingSpotList){
            if(parkingSpot.isAvailable() && parkingSpot.canFitVehicle(vehicle))
                return parkingSpot;
        }
        return null;
    }

    public int getFloorNumber() {
        return floorNumber;
    }
}
