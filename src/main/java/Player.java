import javafx.util.Pair;

import java.util.Scanner;

public class Player {
    private int savedCitizens;
    private int deadCitizens;
    private int remainingRoads;
    private int barricadeCounter;
    private int remainingBarricades;
    private int placedRoads;

    public Player(int remainingRoads, int remainingBarricades) {
        this.savedCitizens = 0;
        this.deadCitizens = 0;
        this.placedRoads = 0;
        this.barricadeCounter = 0;
        this.remainingRoads = remainingRoads;
        this.remainingBarricades = remainingBarricades;
    }

    public void increaseSavedCitizens() {
        this.savedCitizens++;
    }

    public int getSavedCitizens() {
        return savedCitizens;
    }

    public void increaseDeadCitizens() {
        this.deadCitizens++;
    }

    public int getDeadCitizens() {
        return deadCitizens;
    }

    public int getPlacedRoads() {
        return placedRoads;
    }

    public Pair<Boolean, int[]> playerAction() {
        boolean isRoad = false;
        int[] structureCords = new int[2];
        Scanner input = new Scanner(System.in);
        while (true){
            initialPlayerActionsPrint();
            char choice = input.next().charAt(0);
            if(choice == 'S' || choice == 's'){
                remainingRoads++;
                barricadeCounter++;
                if(barricadeCounter%2==0){
                    remainingBarricades++;
                    barricadeCounter=0;
                }
                return null;
            } else if(choice == 'R' || choice == 'r'){
                return placeRoad(structureCords, input);
            } else if(choice == 'B' || choice == 'b'){
                return placeBarrier(structureCords, input);
            }else {
                System.out.println("Error!!! Please input correct choice.");
            }
        }
    }

    private Pair<Boolean, int[]> placeBarrier(int[] structureCords, Scanner input) {
        boolean isRoad;
        if(remainingBarricades > 0){
            isRoad = false;
            System.out.print("Insert x coordinate: ");
            structureCords[0] = input.nextInt();
            System.out.print("Insert y coordinate: ");
            structureCords[1] = input.nextInt();
            remainingBarricades--;
            return new Pair<>(isRoad, structureCords);
        }
        return null;
    }

    private Pair<Boolean, int[]> placeRoad(int[] structureCords, Scanner input) {
        boolean isRoad;
        if(remainingRoads > 0){
            isRoad = true;
            System.out.print("Insert x coordinate: ");
            structureCords[0] = input.nextInt();
            System.out.print("Insert y coordinate: ");
            structureCords[1] = input.nextInt();
            remainingRoads--;
            placedRoads++;
            return new Pair<>(isRoad, structureCords);
        } else {
            System.out.println("You don't have any remaining roads :(");
        }
        return null;
    }

    private void initialPlayerActionsPrint() {
        System.out.println("You have: "+remainingRoads+" remaining roads and: "+remainingBarricades+" remaining barricades");
        System.out.println("Saved Citizens: "+savedCitizens+" | Dead Citizens: "+deadCitizens);
        System.out.println("Input R to build a road OR B to build a barricade OR S to skip this turn");
        System.out.print("Input: ");
    }
}
