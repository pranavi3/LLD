package parkingspot;

import vehicle.Vehicle;
import vehicle.VehicleType;

public class LargeSpot extends ParkingSpot{

    public LargeSpot(String spotId) {
        super(spotId);
    }

    @Override
    public boolean canFitVehicle(Vehicle vehicle) {
        return vehicle.getType() == VehicleType.TRUCK;
    }
}
