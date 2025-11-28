import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class EntityTest {
    @Test
    public void shouldReturnCitizenSymbol(){
        Citizen citizen = new Citizen();
        assertEquals('C', citizen.getSymbol());
    }

    @Test
    public void shouldReturnHouseSymbol(){
        House house = new House();
        assertEquals('H', house.getSymbol());
    }

    @Test
    public void shouldReturnSaveZoneSymbol(){
        SaveZone saveZone = new SaveZone();
        assertEquals('S', saveZone.getSymbol());
    }

    @Test
    public void entitiesShouldReturnZerosIfNoCordsAreSpecified(){
        List<Entity> entities = new ArrayList<Entity>();
        entities.add(new Citizen());
        entities.add(new House());
        entities.add(new SaveZone());
        for(Entity entity : entities){
            assertEquals(0, entity.getX());
            assertEquals(0, entity.getY());
        }
    }

    @Test
    public void citizenShouldReturnDefinedCords(){
        int xTestCord = 1;
        int yTestCord = 2;
        Citizen citizen = new Citizen(xTestCord,yTestCord);
        assertEquals(xTestCord,citizen.getX());
        assertEquals(yTestCord,citizen.getY());
    }

    @Test
    public void houseShouldReturnDefinedCords(){
        int xTestCord = 1;
        int yTestCord = 2;
        House house = new House(xTestCord,yTestCord);
        assertEquals(xTestCord,house.getX());
        assertEquals(yTestCord,house.getY());
    }

    @Test
    public void saveZoneShouldReturnDefinedCords(){
        int xTestCord = 1;
        int yTestCord = 2;
        SaveZone saveZone = new SaveZone(xTestCord,yTestCord);
        assertEquals(xTestCord,saveZone.getX());
        assertEquals(yTestCord,saveZone.getY());
    }
}
