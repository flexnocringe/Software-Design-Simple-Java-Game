import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PlayerStatTest {
    @Test
    public void shouldReturnZeroIfSavedCitizensNotIncreased() {
        int testRoads =2;
        int testBarriers =2;
        Player player = new Player(testRoads, testBarriers);
        assertEquals(0,player.getSavedCitizens());
    }

    @Test
    public void shouldReturnZeroIfDeadCitizensNotIncreased() {
        int testRoads =2;
        int testBarriers =2;
        Player player = new Player(testRoads, testBarriers);
        assertEquals(0,player.getDeadCitizens());
    }

    @Test
    public void shouldReturnSavedCitizens() {
        int testRoads =2;
        int testBarriers =2;
        Player player = new Player(testRoads, testBarriers);
        int testSavedCitizens = 3;
        for(int i = 0; i < testSavedCitizens; i++) {
            player.increaseSavedCitizens();
        }
        assertEquals(testSavedCitizens, player.getSavedCitizens());
    }

    @Test
    public void shouldReturnDeadCitizens() {
        int testRoads =2;
        int testBarriers =2;
        Player player = new Player(testRoads, testBarriers);
        int testDeadCitizens = 2;
        for(int i = 0; i < testDeadCitizens; i++) {
            player.increaseDeadCitizens();
        }
        assertEquals(testDeadCitizens, player.getDeadCitizens());
    }
}
