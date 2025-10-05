import java.util.*;

public class ElevatorCar {
    private final int id;
    private int currentFloor;
    private Direction direction;
    private DoorState doorState;
    private final TreeSet<Integer> upRequests;
    private final TreeSet<Integer> downRequests;
    private final InternalDisplay display;
    private ElevatorState state;

    public ElevatorCar(int id) {
        this.id = id;
        this.currentFloor = 0;
        this.direction = Direction.IDLE;
        this.doorState = DoorState.CLOSED;
        this.upRequests = new TreeSet<>();
        this.downRequests = new TreeSet<>(Collections.reverseOrder());
        this.display = new InternalDisplay(id);
        this.state = ElevatorState.IDLE;
    }

    public void addNewRequest(int floor) {
        if (floor > currentFloor) upRequests.add(floor);
        else if (floor < currentFloor) downRequests.add(floor);
    }

    public void move() {
        if (direction == Direction.UP || direction == Direction.IDLE)
            serveUpRequests();
        else
            serveDownRequests();
    }

    private void serveUpRequests() {
        for (int floor : upRequests) moveToFloor(floor);
        upRequests.clear();
        direction = Direction.DOWN;
        serveDownRequests();
    }

    private void serveDownRequests() {
        for (int floor : downRequests) moveToFloor(floor);
        downRequests.clear();
        direction = Direction.IDLE;
    }

    private void moveToFloor(int floor) {
        state = ElevatorState.MOVING;
        direction = (floor > currentFloor) ? Direction.UP : Direction.DOWN;
        display.updateDirection(direction);
        display.updateFloor(floor);
        currentFloor = floor;
        openDoor();
    }

    private void openDoor() {
        doorState = DoorState.OPEN;
        System.out.println("Elevator " + id + " opened doors at floor " + currentFloor);
        try {
            Thread.sleep(500); // simulate dwell time
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        doorState = DoorState.CLOSED;
        state = ElevatorState.STOPPED;
        display.updateDirection(Direction.IDLE);
    }

    public int getCurrentFloor() { return currentFloor; }
    public Direction getDirection() { return direction; }
    public ElevatorState getState() { return state; }
}
