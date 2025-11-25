public class SaveZone extends Entity {
    private static char  symbol = 'S';

    public SaveZone(int x, int y) {
        super(x, y);
    }

    public SaveZone(){}
    public char getSymbol(){
        return symbol;
    }
}
