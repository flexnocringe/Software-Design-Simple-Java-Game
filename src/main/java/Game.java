import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {

    public GameMap map = new GameMap();
    private Lava lava = new Lava();
    int remainingRoads = 2;
    int remainingBarricades = 2;
    private Player player = new  Player(remainingRoads, remainingBarricades);

    public void start() {
        int lavaCounter = 0;
        int lavaDelay = 5;
        int roadsBeforeIntesnify = 4;
        int lavaIntensify = 2;
        List<Citizen> citizens = new ArrayList<>();
        List<House> houses = map.getHouses();
        House symbolHouse = new House();
        List<SaveZone> saveZones = map.getSaveZones();
        int startCitizens = placeCitizens(houses, citizens);
        while(!map.checkIfMapIsFilledWithLava()){
            citizensMovement(citizens, houses, symbolHouse, saveZones);
            lavaCounter++;
            if(lavaCounter%lavaDelay==0){
                map.setMap(lava.advanceLava(map));
                checkCasualties(citizens);
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
            doPlayerAction(playerAction, citizens);
        }
    }

    public void doPlayerAction(Pair<Boolean, int[]> playerAction, List<Citizen> citizens) {
        if(playerAction !=null){
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

    private void checkCasualties(List<Citizen> citizens) {
        for(int i = 0; i< citizens.size(); i++){
            if(map.getTile(citizens.get(i).getX(), citizens.get(i).getY()) == Lava.getLavaSymbol()){
                citizens.remove(citizens.get(i));
                player.increaseDeadCitizens();
            }
        }
    }

    private void citizensMovement(List<Citizen> citizens, List<House> houses, House symbolHouse, List<SaveZone> saveZones) {
        for(int i = 0; i< citizens.size(); i++) {
            mapCleanup(citizens, houses, symbolHouse, i);
            map.setTile(citizens.get(i).getX(), citizens.get(i).getY(), citizens.get(i).getSymbol());
            checkForSuccess(citizens, saveZones, i);
        }
    }

    private void checkForSuccess(List<Citizen> citizens, List<SaveZone> saveZones, int citizenIndex) {
        for(SaveZone saveZone : saveZones) {
            if(citizens.get(citizenIndex).getX() == saveZone.getX() && citizens.get(citizenIndex).getY() == saveZone.getY()) {
                player.increaseSavedCitizens();
                map.setTile(saveZone.getX(), saveZone.getY(), saveZone.getSymbol());
                citizens.remove(citizens.get(citizenIndex));
                break;
            }
        }
    }

    private void mapCleanup(List<Citizen> citizens, List<House> houses, House symbolHouse, int citizenIndex) {
        int cleanupX = citizens.get(citizenIndex).getX();
        int cleanupY = citizens.get(citizenIndex).getY();
        citizens.get(citizenIndex).makeMove();
        if(cleanupX== houses.get(citizens.get(citizenIndex).getHouseNumber()).getX() && cleanupY== houses.get(citizens.get(citizenIndex).getHouseNumber()).getY()) {
            map.setTile(cleanupX, cleanupY, symbolHouse.getSymbol());
        }
        else{
            map.setTile(cleanupX, cleanupY, map.getRoadSymbol());
        }
    }

    private int placeCitizens(List<House> houses, List<Citizen> citizens) {
        for(House house : houses) {
            citizens.add(new Citizen(house.getX(), house.getY(), houses.indexOf(house)));
        }
        int startCitizens = citizens.size();
        for(Citizen citizen : citizens) {
            citizen.generateShortestPath(map);
        }
        return startCitizens;
    }

    public boolean startScreen(){
        Scanner initialInput = new Scanner(System.in);
        printMainMenuScreen();
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

    private static void printMainMenuScreen() {
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
    }
}
