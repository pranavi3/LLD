public class ExternalDisplay implements Display{
    private final int floorNumber;
    private Direction direction;
    private int elevatorId;
    private String message;
    private ElevatorStatus status;
    public enum ElevatorStatus{
        APPROACHING,
        STOPPED,
        IN_MAINTENANCE,
        NOT_IN_SERVICE
    }
    public ExternalDisplay(int floorNumber){
        this.floorNumber = floorNumber;
        this.direction = Direction.IDLE;
        this.status = ElevatorStatus.STOPPED;
        this.message = "";
    }
    @Override
    public void updateFloor(int floor){
        if(floor == this.floorNumber){
            this.status = ElevatorStatus.APPROACHING;
            showDisplay();
        }
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
        this.status = ElevatorStatus.STOPPED;
        showDisplay();
    }
    public void showDisplay(){
        System.out.println("Floor Number: "+floorNumber);
        System.out.println("Direction: "+direction);
        System.out.println("Status: "+status);
        if(!message.isEmpty()){
            System.out.println("Message: "+message);
        }
        System.out.println("---------------------------");
    }
}