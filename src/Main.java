import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Game game = new Game();
        if(game.startScreen()){
            game.start();
        }
    }
}