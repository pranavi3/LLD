import java.util.*;

public class ElevatorSystem {
    private final List<ElevatorController> controllers;

    public ElevatorSystem(int elevatorCount) {
        controllers = new ArrayList<>();
        for (int i = 0; i < elevatorCount; i++) {
            controllers.add(new ElevatorController(new ElevatorCar(i + 1)));
        }
    }

    public void requestElevator(int floor, Direction dir) {
        ElevatorController best = findBestElevator(floor, dir);
        best.submitRequest(floor);
    }

    private ElevatorController findBestElevator(int floor, Direction dir) {
        ElevatorController best = null;
        int minDistance = Integer.MAX_VALUE;

        for (ElevatorController controller : controllers) {
            ElevatorCar car = controller.getCar();

            if (car.getDirection() == Direction.IDLE) {
                int distance = Math.abs(car.getCurrentFloor() - floor);
                if (distance < minDistance) {
                    minDistance = distance;
                    best = controller;
                }
            } else if (car.getDirection() == dir &&
                       ((dir == Direction.UP && car.getCurrentFloor() <= floor) ||
                        (dir == Direction.DOWN && car.getCurrentFloor() >= floor))) {
                int distance = Math.abs(car.getCurrentFloor() - floor);
                if (distance < minDistance) {
                    minDistance = distance;
                    best = controller;
                }
            }
        }

        return (best != null) ? best : controllers.get(0);
    }

    public void stepAll() {
        for (ElevatorController c : controllers) c.step();
    }
}
