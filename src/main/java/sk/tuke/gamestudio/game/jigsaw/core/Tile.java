package sk.tuke.gamestudio.game.jigsaw.core;

public class Tile {
    private int number;
    private int mark;
    private TileState tileState;

    Tile(){
        number = 0;
    }

    public void generating(Generate generate, int number) {
        if(number > 0){
            this.number = number;
            generate.setNumberCount(generate.getNumberCount() - 1);
        }
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public int getNumber() {
        return number;
    }

    public int getMark() {
        return mark;
    }

    public void setNumber(int number, Class caller) {
        if(caller == ControlGameField.class)
            this.number = number;
        else if(number > 0)
            this.number = number;
    }
    public void setTileState(TileState tileState){
        this.tileState = tileState;
    }

    public TileState getTileState() {
        return tileState;
    }
}
