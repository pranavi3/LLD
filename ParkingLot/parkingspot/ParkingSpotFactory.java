package parkingspot;

public class ParkingSpotFactory {
    public static ParkingSpot createParkingSpot(ParkingSpotTypes type, String id){
        return switch (type){
            case BIKE -> new BikeSpot(id);
            case COMPACT -> new CompactSpot(id);
            case LARGE -> new LargeSpot(id);
            default -> throw new IllegalArgumentException("Unknown parking spot type: " + type);
        };
    }
}
