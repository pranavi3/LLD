package fees;

import parking.ParkingTicket;
import vehicle.VehicleType;

import java.util.Map;

public class VehicleBasedFeeStrategy implements FeeStrategy{

    private final Map<VehicleType, Double> hourlyRates = Map.of(
            VehicleType.CAR, 20.0,
            VehicleType.BIKE, 10.0,
            VehicleType.TRUCK, 30.0
    );

    @Override
    public double calculateFee(ParkingTicket parkingTicket) {
        long duration = parkingTicket.getExitTime() - parkingTicket.getEntryTime();
        long hours = (duration / (1000 * 60 * 60)) + 1;
        return hours * hourlyRates.get(parkingTicket.getVehicle().getType());
    }
}
