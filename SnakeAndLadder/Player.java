import java.util.*;
public class Player{
    private final String name;
    private final String id;
    private PlayerStatus status;

    public enum PlayerStatus {
        WAITING,
        PLAYING,
        FINISHED
    }
    Player(String name){
        if(name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException("Player name cannot be null or empty");
        }
        this.name = name.trim();
        this.id = UUID.randomUUID().toString();
        this.status = PlayerStatus.WAITING;
    }
    public String getName(){
        return name;
    }
    public String getId(){
        return id;  
    }
    public PlayerStatus getStatus(){
        return status;
    }
    public void setStatus(PlayerStatus status){
        if(status == null){
            throw new IllegalArgumentException("Player status cannot be null");
        }
        this.status = status;   
    }
    @Override
    public String toString(){
        return "Player{ name = "+ name + ", id = " + id + ", status = " + status + "}";
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return id.equals(player.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}