public class Floor {
    private final int floorNumber;
    private final ExternalDisplay display;

    public Floor(int number) {
        this.floorNumber = number;
        this.display = new ExternalDisplay(number);
    }

    public int getFloorNumber() { return floorNumber; }
}
