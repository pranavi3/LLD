public class ElevatorButton implements Button {
    private boolean isPressed;
    private final int targetFloor;

    public ElevatorButton(int targetFloor) {
        this.targetFloor = targetFloor;
        this.isPressed = false;
    }

    @Override
    public void press() {
        this.isPressed = true;
        System.out.println("Elevator button for floor " + targetFloor + " pressed.");
    }

    @Override
    public void reset() {
        this.isPressed = false;
        System.out.println("Elevator button for floor " + targetFloor + " reset.");
    }

    @Override
    public boolean isPressed() {
        return this.isPressed;
    }

    public int getTargetFloor() {
        return targetFloor;
    }
}
