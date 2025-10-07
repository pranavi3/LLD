package parkingspot;

import vehicle.Vehicle;
import vehicle.VehicleType;

public class CompactSpot extends ParkingSpot{

    public CompactSpot(String spotId) {
        super(spotId);
    }

    @Override
    public boolean canFitVehicle(Vehicle vehicle) {
        return vehicle.getType() == VehicleType.CAR;
    }
}
