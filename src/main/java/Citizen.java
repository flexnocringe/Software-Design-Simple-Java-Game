import java.util.List;

public class Citizen extends Entity {
    protected static char symbol='C';
    private List<int[]> shortestPath;
    private int houseNumber;

    MovementStrategy movementStrategy = new ShortestPathMovementStrategy();

    public Citizen(int x, int y, int houseNumber) {
        super(x, y);
        this.houseNumber = houseNumber;
    }

    public Citizen(int x, int y) {
        super(x, y);
    }

    public Citizen(){}

    public void generateShortestPath(GameMap map){
        shortestPath = PathFinder.findShortestPath(map, this);
    }

    public void makeMove(){
        shortestPath = movementStrategy.move(this, shortestPath);
    }

    public char getSymbol(){
        return symbol;
    }

    public int getHouseNumber() {
        return houseNumber;
    }
}
