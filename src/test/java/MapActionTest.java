import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MapActionTest {
    private char[][] sampleMap = {
            {' ', '1', '2', '3', '4'},
            {'1', ' ', 'H', 'R', 'R'},
            {'2', ' ', 'R', ' ', 'R'},
            {'3', ' ', 'R', ' ', 'R'},
            {'4', ' ', 'S', 'R', 'R' }
    };
    private GameMap map = new GameMap();
    @Test
    public void newTileShouldAppear(){
        map.setMap(sampleMap);
        map.setTile(2,1,'T');
        assertEquals('T',map.getTile(2,1));
    }

    @Test
    public void shouldReturnFalseBecauseNotFilledWithLava(){
        map.setMap(sampleMap);
        assertFalse(map.checkIfMapIsFilledWithLava());
    }

    @Test
    public void shouldReturnTrueBecauseFilledWithLava(){
        char[][] lavaMap = {
                {' ', '1', '2'},
                {'1', 'L', 'L'},
                {'2', 'L', 'L'},
        };
        map.setMap(lavaMap);
        assertTrue(map.checkIfMapIsFilledWithLava());
    }

    @Test
    public void shouldReturnHouseOnMap(){
        map.setMap(sampleMap);
        List<House> testHouses = map.getHouses();
        List<House> expectedHouses = new ArrayList<House>();
        expectedHouses.add(new House(1,2));
        assertEquals(expectedHouses.size(), testHouses.size());
        for( int i = 0; i < expectedHouses.size(); i++ ){
            assertEquals(expectedHouses.get(i).getSymbol(), testHouses.get(i).getSymbol());
            assertEquals(expectedHouses.get(i).getX(), testHouses.get(i).getX());
            assertEquals(expectedHouses.get(i).getY(), testHouses.get(i).getY());
        }
    }

    @Test
    public void shouldReturnSaveZoneOnMap(){
        map.setMap(sampleMap);
        List<SaveZone> testSaveZones = map.getSaveZones();
        List<SaveZone> expectedSaveZones = new ArrayList<SaveZone>();
        expectedSaveZones.add(new SaveZone(4,2));
        assertEquals(expectedSaveZones.size(), testSaveZones.size());
        for(int i = 0; i < expectedSaveZones.size(); i++ ){
            assertEquals(expectedSaveZones.get(i).getSymbol(), testSaveZones.get(i).getSymbol());
            assertEquals(expectedSaveZones.get(i).getX(), testSaveZones.get(i).getX());
            assertEquals(expectedSaveZones.get(i).getY(), testSaveZones.get(i).getY());
        }
    }
}
