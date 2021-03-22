package sk.tuke.gamestudio.game.jigsaw.core;

public class Input {
    private GameField gameField;

    public Input(GameField gameField){
        this.gameField = gameField;
    }

    public void inputNumber(int row, int collumn, int number){
        gameField.getTile()[row][collumn].setNumber(number, getClass());
    }
}