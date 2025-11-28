import java.util.List;

public class ShortestPathMovementStrategy implements MovementStrategy {
    @Override
    public List<int[]> move(Citizen citizen, List<int[]> path) {
        if(!path.isEmpty()){
            citizen.setX(path.getFirst()[0]);
            citizen.setY(path.getFirst()[1]);
            path.removeFirst();
        }
        return path;
    }
}
