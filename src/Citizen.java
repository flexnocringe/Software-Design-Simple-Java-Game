import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Citizen extends Entity {
    protected static char symbol='C';
    private List<int[]> shortestPath;
    private int houseNumber;

    public Citizen(int x, int y, int houseNumber) {
        super(x, y);
        this.houseNumber = houseNumber;
    }

    public void generateShortestPath(GameMap map){
        shortestPath = PathFinder.findShortestPath(map, this);
    }

    public void makeMove(){
        if(!shortestPath.isEmpty()){
            this.x = shortestPath.getFirst()[0];
            this.y = shortestPath.getFirst()[1];
            shortestPath.removeFirst();
        }
    }

    public char getSymbol(){
        return symbol;
    }

    public int getHouseNumber() {
        return houseNumber;
    }
}
