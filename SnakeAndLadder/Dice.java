import java.util.Random;
public class Dice{
    private final int numberOfDices;
    public Dice(int numOfDices){
        if(numOfDices <= 0){
            throw new IllegalArgumentException("Number of dices must be positive");
        }
        this.numberOfDices = numOfDices;
    }
    public int roll(){
        Random rand = new Random();
        int sum = 0;
        for(int i=0; i<numberOfDices; i++){
            sum += rand.nextInt(6) + 1; // Dice roll between 1 and 6 
        }
        return sum;
    }
}