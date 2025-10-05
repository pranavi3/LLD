public class ElevatorController {
    private final ElevatorCar elevator;

    public ElevatorController(ElevatorCar elevator) {
        this.elevator = elevator;
    }

    public void submitRequest(int floor) {
        elevator.addNewRequest(floor);
    }

    public void step() {
        elevator.move();
    }

    public ElevatorCar getCar() {
        return elevator;
    }
}
