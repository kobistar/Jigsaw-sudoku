package sk.tuke.gamestudio.game.jigsaw.core;

public class Field {
    private GameState state = GameState.PLAYING;

    private final int rowCount;
    private final int columnCount;
    private final int numberCount;
    private final Tile[][] tiles;

    public Field(GameState state, int rowCount, int columnCount, int numberCount) {
        this.state = state;
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        this.numberCount = numberCount;
        tiles = new Tile[rowCount][columnCount];
        generate();
    }
    public void generate(){

    }

    public GameState getState() {
        return state;
    }

    public int getRowCount() {
        return rowCount;
    }

    public int getColumnCount() {
        return columnCount;
    }

    public int getNumberCount() {
        return numberCount;
    }

}
