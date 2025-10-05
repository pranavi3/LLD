public class EmergencyButton implements Button{
    private boolean isPressed;
    private final int elevatorId;

    public EmergencyButton(int elevatorId){
        this.isPressed = false;
        this.elevatorId = elevatorId;
    }
    @Override
    public void press(){
        this.isPressed = true;
        System.out.println("Emergency button in elevator "+elevatorId+" pressed.");
        alertAuthorities();
    }
    @Override
    public void reset(){
        this.isPressed = false;
        System.out.println("Emergency button reset.");
    }
    @Override
    public boolean isPressed(){
        return this.isPressed;  
    }
    public int getElevatorId(){
        return this.elevatorId;
    }

    private void alertAuthorities(){
        // Logic to alert emergency services
        System.out.println("Authorities alerted for elevator "+elevatorId);
    }
}