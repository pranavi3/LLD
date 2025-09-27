import java.util.*;
public class Game{
    private static final int DEFAULT_BOARD_SIZE = 100;
    private static final int DEFAULT_NUMBER_OF_DICES = 2;
    
    private final Board board;

    public Game(List<Player> players){ 
        this(players, DEFAULT_BOARD_SIZE, DEFAULT_NUMBER_OF_DICES);
    }

    public Game(List<Player> players, int boardSize, int numOfDices) {
        if (players == null || players.size() < 2) {
            throw new IllegalArgumentException("Game must have at least two players");
        }
        if (boardSize <= 0) {
            throw new IllegalArgumentException("Board size must be positive");
        }
        if (numOfDices <= 0) {
            throw new IllegalArgumentException("Number of dices must be positive");
        }

        List<Coordinates> snakes = initializeSnakes();
        List<Coordinates> ladders = initializeLadders();
        Map<String, Integer> playerPositions = initializePlayerPositions(players);
        Queue<String> turnOrder = new LinkedList<>();
        for (Player player : players) {
            turnOrder.offer(player.getName());
        }

        this.board = new Board(boardSize, snakes, ladders, playerPositions, turnOrder, numOfDices);
    }

    private List<Coordinates> initializeSnakes(){
        List<Coordinates> snakes = new ArrayList<>();
        Coordinates snake1 = new Coordinates(14, 7);
        Coordinates snake2 = new Coordinates(31, 26);
        Coordinates snake3 = new Coordinates(78, 39);
        Coordinates snake4 = new Coordinates(92, 70);
        snakes.add(snake1);
        snakes.add(snake2);
        snakes.add(snake3);
        snakes.add(snake4);
        return snakes;
    }
    private List<Coordinates> initializeLadders(){
        List<Coordinates> ladders = new ArrayList<>();
        Coordinates ladder1 = new Coordinates(3, 22);
        Coordinates ladder2 = new Coordinates(5, 8);
        Coordinates ladder3 = new Coordinates(20, 29);
        Coordinates ladder4 = new Coordinates(27, 56);
        ladders.add(ladder1);
        ladders.add(ladder2);
        ladders.add(ladder3);
        ladders.add(ladder4);
        return ladders;
    }
    private Map<String, Integer> initializePlayerPositions(List<Player> players){
        Map<String, Integer> playerPositions = new HashMap<>();
        for(Player player: players){
            playerPositions.put(player.getName(), 0);
        }
        return playerPositions;
    }
    public void start(){
        board.start();
    }
    public static void main(String[] args){
        List<Player> players = new ArrayList<>();
        players.add(new Player("Alice"));
        players.add(new Player("Bob"));
        Game game = new Game(players);
        game.start();
    }
}