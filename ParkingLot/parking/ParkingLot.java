package parking;

import fees.FeeStrategy;
import fees.FlatRateFeeStrategy;
import parkingspot.ParkingSpot;
import vehicle.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ParkingLot {
    private static ParkingLot INSTANCE;
    private final List<ParkingFloor> parkingFloors = new ArrayList<>();
    private FeeStrategy feeStrategy;
    private final Map<String, ParkingTicket> activeTickets = new ConcurrentHashMap<>();

    private ParkingLot(){
        feeStrategy = new FlatRateFeeStrategy();
    }
    public static ParkingLot getInstance(){
        if(INSTANCE == null){
            synchronized (ParkingLot.class){
                if(INSTANCE == null)
                    INSTANCE = new ParkingLot();
            }
        }
        return INSTANCE;
    }

    public void addFloor(ParkingFloor floor) {
        parkingFloors.add(floor);
    }
    public void setFeeStrategy(FeeStrategy feeStrategy){
        this.feeStrategy = feeStrategy;
    }
    public synchronized ParkingTicket parkVehicle(Vehicle vehicle) throws Exception {
        for(ParkingFloor floor: parkingFloors){
            ParkingSpot parkingSpot = floor.getAvailableSpot(vehicle);
            if (parkingSpot != null){
                if(parkingSpot.assignVehicle(vehicle)){
                    ParkingTicket ticket = new ParkingTicket(vehicle, parkingSpot);
                    activeTickets.put(vehicle.getLicensePlate(), ticket);
                    return ticket;
                }
            }
        }
        throw new Exception("No available spot for " + vehicle.getType());
    }
    public synchronized double unParkVehicle(String licence) throws Exception {
        ParkingTicket parkingTicket = activeTickets.remove(licence);
        if (parkingTicket == null)
            throw new Exception("No ticket available");
        parkingTicket.getParkingSpot().removeVehicle();
        parkingTicket.setExitTime();
        return feeStrategy.calculateFee(parkingTicket);
    }
}
