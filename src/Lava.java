public class Lava {
    private static final char symbol = 'L';

    public static char getLavaSymbol() {
        return symbol;
    }

    public char[][] advanceLava(GameMap gameMap){
        for(int i=1; i<gameMap.getMapWidth(); i++){
            for(int j=1; j<gameMap.getMapHeight(); j++){
                if(gameMap.getTile(j,i) != symbol && gameMap.getTile(j,i) != gameMap.getBarricadeSymbol() && gameMap.getTile(j,i)!=SaveZone.getSaveZoneSymbol()){
                    gameMap.setTile(j, i ,symbol);
                    break;
                } else if(gameMap.getTile(j,i) == gameMap.getBarricadeSymbol()){
                    gameMap.setTile(j, i , gameMap.getEmptySymbol());
                    break;
                }
            }
        }
        return gameMap.getMap();
    }
}
