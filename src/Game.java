import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {

    private GameMap map = new GameMap();
    private Lava lava = new Lava();
    private Player player = new  Player(2, 3);

    public void start() {
        int lavaCounter = 0;
        int lavaDelay = 3;
        int roadsBeforeIntesnify = 5;
        int lavaIntensify = 2;
        List<Citizen> citizens = new ArrayList<>();
        List<House> houses = map.getHouses();
        List<SaveZone> saveZones = map.getSaveZones();
        for(House house : houses) {
            citizens.add(new Citizen(house.getX(), house.getY(), houses.indexOf(house)));
        }
        int startCitizens = citizens.size();
        for(Citizen citizen : citizens) {
            citizen.generateShortestPath(map);
        }
        while(!map.checkIfMapIsFilledWithLava()){
            for(int i=0; i<citizens.size(); i++) {
                int cleanupX = citizens.get(i).getX();
                int cleanupY = citizens.get(i).getY();
                citizens.get(i).makeMove();
                if(cleanupX==houses.get(citizens.get(i).getHouseNumber()).getX() && cleanupY==houses.get(citizens.get(i).getHouseNumber()).getY()) {
                    map.setTile(cleanupX, cleanupY, House.getHouseSymbol());
                }
                else{
                    map.setTile(cleanupX, cleanupY, map.getRoadSymbol());
                }
                map.setTile(citizens.get(i).getX(), citizens.get(i).getY(), citizens.get(i).getSymbol());
                for(SaveZone saveZone : saveZones) {
                    if(citizens.get(i).getX() == saveZone.getX() && citizens.get(i).getY() == saveZone.getY()) {
                        player.increaseSavedCitizens();
                        map.setTile(saveZone.getX(), saveZone.getY(), SaveZone.getSaveZoneSymbol());
                        citizens.remove(citizens.get(i));
                        break;
                    }
                }
            }
            lavaCounter++;
            if(lavaCounter%lavaDelay==0){
                map.setMap(lava.advanceLava(map));
                for(int i=0; i<citizens.size(); i++){
                    if(map.getTile(citizens.get(i).getX(), citizens.get(i).getY()) == Lava.getLavaSymbol()){
                        citizens.remove(citizens.get(i));
                        player.increaseDeadCitizens();
                    }
                }
                if(player.getPlacedRoads()>=roadsBeforeIntesnify){
                    lavaDelay=lavaIntensify;
                }
                lavaCounter = 0;
            }
            map.printMap();
            if(player.getSavedCitizens() == startCitizens){
                System.out.println("Good Job!! You saved everybody");
                break;
            } else if(player.getSavedCitizens()+ player.getDeadCitizens() == startCitizens) {
                System.out.println("Game Over! At least you got some Citizens saved");
                System.out.println("Final Score: saved: "+player.getSavedCitizens()+" | dead: "+player.getDeadCitizens());
                break;
            } else if(player.getDeadCitizens() == startCitizens) {
                System.out.println("Game Over!!! Everybody is DEAD :(");
                break;
            }
            Pair<Boolean, int[]> playerAction = player.playerAction();
            if(playerAction!=null){
                if(playerAction.getKey())
                {
                    map.setTile(playerAction.getValue()[0]+1, playerAction.getValue()[1]+1, map.getRoadSymbol());
                } else{
                    map.setTile(playerAction.getValue()[0]+1, playerAction.getValue()[1]+1, map.getBarricadeSymbol());
                }
                for(Citizen citizen : citizens) {
                    citizen.generateShortestPath(map);
                }
            }
        }
    }

    public boolean startScreen(){
        Scanner initialInput = new Scanner(System.in);
        char[][] menuScreen = {
                {'^','L','A','V','A',' ',' ',' ',' ',' '},
                {' ','*','R','E','S','C','U','E','\'',' '},
                {' ','\'',' ','"','^',' ','*',' ',' ',' '},
                {' ','*','/','\'','/','\\','"','*',' ',' '},
                {'^','_','/',' ',' ',' ','^','\\','_','\''}
        };
        for(int i =0; i<menuScreen.length;i++){
            for(int j =0; j<menuScreen[0].length;j++){
                System.out.print(menuScreen[i][j]);
            }
            System.out.println();
        }
        System.out.println("Press P to play OR Q to quit");
        while(true){
            System.out.print("Input: ");
            char choice = initialInput.next().charAt(0);
            if(choice=='P' ||  choice=='p'){
                return true;
            } else if(choice=='Q' ||  choice=='q'){
                return false;
            } else{
                System.out.println("Invalid input :(");
            }
        }

    }
}
