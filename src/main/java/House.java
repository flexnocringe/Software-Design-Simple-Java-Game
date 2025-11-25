public class House extends Entity{
    private static char symbol = 'H';

    public House(int x, int y) {
        super(x, y);
    }

    public House(){}

    public char getSymbol() {
        return symbol;
    }
}
