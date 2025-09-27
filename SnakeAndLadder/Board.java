import java.util.*;
public class Board{
    private final int size;
    private final List<Coordinates> snakes;
    private final List<Coordinates> ladders;
    private final Map<String, Integer> playerPositions;
    private final Queue<String> turnOrder;
    private final Dice dice;
    Board(int size, List<Coordinates> snakes, List<Coordinates> ladders, Map<String, Integer> playerPositions, Queue<String> turnOrder, int numOfDices){
        if(size <= 0){
            throw new IllegalArgumentException("Board size must be positive");
        }
        if(snakes == null || ladders == null || playerPositions == null || turnOrder == null){
            throw new IllegalArgumentException("Snakes, Ladders, Player Positions and Turn Order cannot be null");
        }
        this.size = size;
        this.snakes = new ArrayList<>(snakes);
        this.ladders = new ArrayList<>(ladders);
        this.playerPositions = new HashMap<>(playerPositions);
        this.turnOrder = new LinkedList<>(turnOrder);
        this.dice = new Dice(numOfDices);

        validateBoard();
    }
    public void validateBoard(){
        for(Coordinates snake: snakes){
            if(snake.getStart() <= snake.getEnd()){
                throw new IllegalArgumentException("Invalid snake positions: start must be greater than end");
            }
        }
        for(Coordinates ladder: ladders){
            if(ladder.getStart() >= ladder.getEnd()){
                throw new IllegalArgumentException("Invalid ladder positions: start must be less than end");
            }
        }
    }
    public void start(){
        boolean gameWon = false;
        while(!gameWon && !turnOrder.isEmpty()){
            gameWon = playTurn();
        }
    }
    private boolean playTurn(){
        String currentPlayer = turnOrder.poll();
        int currentPosition = playerPositions.get(currentPlayer);
        System.out.println("Current Player: "+currentPlayer +" Current Position: "+currentPosition);
        int diceRoll = dice.roll();
        System.out.println("Dice Roll: "+diceRoll);

        int newPosition = currentPosition + diceRoll;
        if(newPosition > size){
            System.out.println("Roll exceeds board size. Try again next turn.");
            turnOrder.offer(currentPlayer);
        }else if(newPosition == size){
            System.out.println(currentPlayer+" wins the game!");
            playerPositions.put(currentPlayer, newPosition);
            return true;  // Game is won
        }else{
                int oldPosition = newPosition;
                for(Coordinates snake: snakes){
                    if(snake.getStart() == newPosition){
                        newPosition = snake.getEnd();
                        System.out.println("Bitten by a snake! New Position: "+newPosition); 
                        break;   
                    }
                }
                if(oldPosition == newPosition){
                    for(Coordinates ladder: ladders){
                        if(ladder.getStart() == newPosition){
                            newPosition = ladder.getEnd();
                            System.out.println("Climbed a ladder! New Position: "+newPosition); 
                            if(newPosition == size){
                                System.out.println(currentPlayer+" wins the game!");
                            break;
                        }
                    }
                }
                if(newPosition != size){
                    playerPositions.put(currentPlayer, newPosition);
                    turnOrder.offer(currentPlayer);
                }
            }
            System.out.println(currentPlayer+" moved to position "+newPosition);
            System.out.println("--------------------------------------------------");
        }
        return false;  // Turn completed without a win
    }
}