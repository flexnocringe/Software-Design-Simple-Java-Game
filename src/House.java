public class House extends Entity{
    private static char symbol = 'H';

    public House(int x, int y) {
        super(x, y);
    }

    public static char getHouseSymbol() {
        return symbol;
    }
}
