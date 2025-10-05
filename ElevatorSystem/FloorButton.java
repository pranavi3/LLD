public class FloorButton implements Button{
    private boolean isPressed;
    private final Direction direction;
    private final int currentFloor;

    public FloorButton(int currentFloor, Direction direction){
        this.currentFloor = currentFloor;
        this.direction = direction;
        this.isPressed = false;
    }
    @Override
    public void press(){
        this.isPressed = true;
        System.out.println("Floor button for floor "+currentFloor+" in direction "+direction+" pressed");
    }
    @Override
    public void reset(){
        this.isPressed = false;
        System.out.println("Floor button for floor "+currentFloor+" in direction "+direction+" reset");
    }
    @Override
    public boolean isPressed(){
        return this.isPressed;  
    }
    public Direction getDirection(){
        return this.direction;
    }
    public int getCurrentFloor(){
        return this.currentFloor;
    }
}