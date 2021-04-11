package sk.tuke.gamestudio.game.jigsaw.core;

public class ControlGameField {
    private GameField gameField;

    private int[][] puzzle = new int[9][9];

    public ControlGameField(GameField gameField){
        this.gameField = gameField;
    }

    public boolean controlInput(int currentX, int currentY, int wantNumber) {
        boolean status = true;
        for (int i = 0; i < 9; i++) {
            if (status) {
                status = controlField(wantNumber, currentX, i);
            } else break;
            if (status) {
                status = controlField(wantNumber, i, currentY);
            } else break;
        }
        return status;
    }

    private boolean controlField(int wantNumber, int x, int y){
        return wantNumber != gameField.getTile()[x][y].getNumber();
    }

    private boolean control(){
        boolean status = true;
        for(int row = 0; row < 9; row++){
            for(int column = 0; column < 9; column++){
                if(status){
                    status = controlInput(row, column, gameField.getTile()[row][column].getNumber());
                }else break;
            }
        }
        return status;
    }

    public void controlPuzzle(){
        for(int row = 0; row < 9; row++){
            for(int column = 0; column < 9; column++) {
                if (gameField.getTile()[row][column].getNumber() > 0){
                    if(puzzle[ (gameField.getTile()[row][column].getMark() - 1) ][ (gameField.getTile()[row][column].getNumber() - 1) ] ==  gameField.getTile()[row][column].getNumber())
                        gameField.getTile()[row][column].setNumber(0, getClass());
                    else
                        puzzle[ (gameField.getTile()[row][column].getMark() - 1) ][ (gameField.getTile()[row][column].getNumber() - 1) ] = gameField.getTile()[row][column].getNumber();
                }
            }
        }
    }

    public boolean insertToPuzzle(int row, int column, int wantNumber){
        if(puzzle[(gameField.getTile()[row][column].getMark() - 1)][wantNumber - 1] != wantNumber){
            puzzle[(gameField.getTile()[row][column].getMark() - 1)][wantNumber - 1] = wantNumber;
            return true;
        }
        return false;
    }

    public boolean winGame(){
        boolean status = false;
        for(int row = 0; row < 9; row++){
            for(int column = 0; column < 9; column++){
                if((gameField.getTile()[row][column].getNumber() == 0) || (puzzle[row][column] == 0)) {
                    return false;
                }else{
                    status = true;
                }
            }
        }
        if(status && control()){
            status = true;
        }
        return status;
    }

}

