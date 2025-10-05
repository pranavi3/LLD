public class DoorButton implements Button {
    private boolean isPressed;
    private final int elevatorId;
    private final DoorState operation;

    public DoorButton(int elevatorId, DoorState operation) {
        this.isPressed = false;
        this.elevatorId = elevatorId;
        this.operation = operation;
    }

    @Override
    public void press() {
        this.isPressed = true;
        System.out.println("Door button (" + operation + ") in elevator " + elevatorId + " pressed.");
        handleDoorOperation();
    }

    @Override
    public void reset() {
        this.isPressed = false;
        System.out.println("Door button reset.");
    }

    @Override
    public boolean isPressed() {
        return this.isPressed;
    }

    private void handleDoorOperation() {
        switch (operation) {
            case OPEN -> System.out.println("Door opening for elevator " + elevatorId);
            case CLOSED -> System.out.println("Door closing for elevator " + elevatorId);
        }
    }
}
