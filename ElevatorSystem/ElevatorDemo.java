public class ElevatorDemo {
    public static void main(String[] args) {
        ElevatorSystem system = new ElevatorSystem(2);

        system.requestElevator(5, Direction.UP);
        system.requestElevator(2, Direction.DOWN);

        system.stepAll();
    }
}
