import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ShortestPathGeneratorTest {
    @Test
    public void shouldReturnShortestPath() {
        GameMap map = new GameMap();
        char[][] sampleMap = {
                {' ', '1', '2', '3', '4'},
                {'1', ' ', 'H', 'R', 'R'},
                {'2', ' ', 'R', ' ', 'R'},
                {'3', ' ', 'R', ' ', 'R'},
                {'4', ' ', 'S', 'R', 'R' }
        };
        map.setMap(sampleMap);
        Citizen citizen = new Citizen(1,2);
        List<int[]> path = new ArrayList<int[]>();
        path.add(new int[] {2,2});
        path.add(new int[] {3,2});
        path.add(new int[] {4,2});
        List<int[]> testablePath = PathFinder.findShortestPath(map, citizen);
        for(int i =0; i<path.size();i++){
            assertEquals(path.get(i)[0],testablePath.get(i)[0]);
            assertEquals(path.get(i)[1],testablePath.get(i)[1]);
        }
    }

    @Test
    public void shouldReturnEmptyListIfNoPathFound() {
        GameMap map = new GameMap();
        char[][] sampleMap = {
                {' ', '1', '2', '3', '4'},
                {'1', ' ', 'H', 'R', 'R'},
                {'2', ' ', 'R', ' ', ' '},
                {'3', ' ', ' ', ' ', 'R'},
                {'4', ' ', 'S', 'R', 'R' }
        };
        map.setMap(sampleMap);
        Citizen citizen = new Citizen(1,2);
        List<int[]> emptyList = new ArrayList<int[]>();
        assertEquals(emptyList, PathFinder.findShortestPath(map, citizen));
    }
}
