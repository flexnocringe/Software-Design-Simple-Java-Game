import java.util.List;

public interface MovementStrategy {
    public List<int[]> move(Citizen citizen, List<int[]> path);
}
