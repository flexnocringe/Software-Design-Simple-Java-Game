import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LavaTest {
    @Test
    public void checkIfLavaIsAdvancing(){
        char[][] sampleMap = {
                {' ', '1', '2', '3', '4'},
                {'1', ' ', 'H', 'R', 'R'},
                {'2', ' ', 'R', ' ', 'R'},
                {'3', ' ', 'R', ' ', 'R'},
                {'4', ' ', 'S', 'R', 'R' }
        };
        GameMap map = new GameMap();
        Lava lava = new Lava();
        int lavaLevel = 1;
        while(map.checkIfMapIsFilledWithLava()){
            map.setMap(lava.advanceLava(map));
            for(int i = 0; i < map.getMapWidth(); i++){
                assertEquals('L', sampleMap[i][lavaLevel]);
            }
            lavaLevel++;
        }
    }
}
