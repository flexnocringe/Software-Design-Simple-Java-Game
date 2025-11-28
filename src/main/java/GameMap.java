import java.util.ArrayList;
import java.util.List;

public class GameMap {
    private char roadSymbol = 'R';
    private char barricadeSymbol = '#';
    private char emptySymbol = ' ';
    private char[][] map = {
            {' ', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'},
            {'0', 'H', ' ', 'H', 'R', ' ', ' ', ' ', 'H', ' ', ' '},
            {'1', 'R', ' ', ' ', 'R', 'R', 'H', ' ', 'R', 'R', 'H'},
            {'2', 'R', 'R', ' ', 'R', ' ', ' ', ' ', ' ', 'R', ' '},
            {'3', ' ', 'R', ' ', 'R', 'R', 'R', ' ', ' ', 'R', ' '},
            {'4', ' ', 'R', ' ', ' ', ' ', 'R', ' ', 'R', 'R', ' '},
            {'5', ' ', 'R', 'R', 'R', ' ', 'R', ' ', ' ', ' ', ' '},
            {'6', ' ', ' ', ' ', 'R', ' ', 'R', 'R', 'R', ' ', ' '},
            {'7', ' ', ' ', ' ', 'R', ' ', ' ', ' ', 'R', 'R', 'R'},
            {'8', ' ', 'S', ' ', 'R', 'R', 'R', ' ', 'R', ' ', 'R'},
            {'9', ' ', ' ', ' ', ' ', ' ', ' ', 'S', 'R', ' ', 'S'}
    };

    public void printMap(){
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[i].length;j++){
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }

    public char getTile(int x, int y){
        return map[x][y];
    }

    public void setTile(int x, int y, char tile){
        map[x][y] = tile;
    }

    public List<House> getHouses(){
        List<House> houses = new ArrayList<>();
        House symbolHouse = (House) EntityFactory.createEntity(EntityType.HOUSE);
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[i].length;j++){
                if(map[i][j]==symbolHouse.getSymbol()){
                    houses.add((House) EntityFactory.createEntity(EntityType.HOUSE, i, j));
                }
            }
        }
        return houses;
    }

    public List<SaveZone> getSaveZones(){
        List<SaveZone> saveZones = new ArrayList<>();
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[i].length;j++){
                if(map[i][j]=='S'){
                    saveZones.add((SaveZone) EntityFactory.createEntity(EntityType.SAVEZONE, i, j));
                }
            }
        }
        return saveZones;
    }

    public char[][] getMap() {
        return map;
    }

    public void setMap(char[][] map) {
        this.map = map;
    }

    public int getMapWidth(){
        return map[0].length;
    }

    public int getMapHeight(){
        return map.length;
    }

    public char getRoadSymbol() {
        return roadSymbol;
    }

    public char getBarricadeSymbol() {
        return barricadeSymbol;
    }

    public char getEmptySymbol() {
        return emptySymbol;
    }

    public boolean checkIfMapIsFilledWithLava(){
        int filledCounter = 0;
        SaveZone symbolSaveZone = new SaveZone();
        for(int i=1; i<getMapWidth(); i++){
            if(map[getMapHeight()-1][i] == symbolSaveZone.getSymbol() || map[getMapHeight()-1][i] == Lava.getLavaSymbol()){
                filledCounter++;
            }
        }
        if(filledCounter==getMapWidth()-1){
            System.out.println("Game Over!!! The map is filled with Lava");
            return true;
        } else {
            return false;
        }
    }
}
