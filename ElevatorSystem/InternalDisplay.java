public class InternalDisplay implements Display{
    private int currentFloor;
    private Direction direction;
    private String message;
    private boolean isOverweight;
    private boolean isEmergency;
    private int elevatorId;
    public InternalDisplay(int elevatorId){
        this.elevatorId = elevatorId;
        this.currentFloor = 1;
        this.direction = Direction.IDLE;
        this.message = "";
        this.isOverweight = false;
        this.isEmergency = false;
    }
    @Override
    public void updateFloor(int floor){
        this.currentFloor = floor;
        showDisplay();
    }
    @Override
    public void updateDirection(Direction direction){
        this.direction = direction;
        showDisplay();
    }
    @Override
    public void showMessage(String message){
        this.message = message;
        showDisplay();      
    }
    @Override
    public void clear(){
        this.message = "";
        showDisplay();
    }
    public void showDisplay(){
        System.out.println("Elevator ID: "+elevatorId);
        System.out.println("Current Floor: "+currentFloor);
        System.out.println("Direction: "+direction);
        if(!message.isEmpty()){
            System.out.println("Message: "+message);
        }
        if(isOverweight){
            System.out.println("Warning: Overweight!");
        }
        if(isEmergency){
            System.out.println("Emergency Mode Activated!");
        }
        System.out.println("---------------------------");
    }
    public void showOverweightWarning(boolean status){
        this.isOverweight = status;
        showDisplay();
    }
    public void showEmergencyMode(boolean status){
        this.isEmergency = status;
        showDisplay();
    }
    
}