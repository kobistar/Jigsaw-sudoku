
package sk.tuke.gamestudio.game.jigsaw.core;
import java.util.Random;

public class Generate {
    private int numberCount;
    private GameField gameField;
    private static final int[][] markAreaOne = new int[][]{
            {1,2,2,2,2,2,3,4,4},
            {1,1,2,3,3,3,3,4,4},
            {1,1,2,3,8,8,3,3,4},
            {1,1,2,3,8,4,4,4,4},
            {1,1,2,8,8,8,6,7,7},
            {5,5,5,5,8,9,6,7,7},
            {5,9,9,8,8,9,6,7,7},
            {5,5,9,9,9,9,6,7,7},
            {5,5,9,6,6,6,6,6,7}
    };

    Generate(GameField gameField, int numberCount){
        this.numberCount = numberCount;
        this.gameField = gameField;
    }

    public void generatePlayground(){
        int number;
        for(int idx = 0; idx < 9; idx++){
            for(int idx_two = 0; idx_two < 9; idx_two++) {
                number = generateNumber();
                if (new ControlGameField(gameField).controlInput(idx, idx_two, number)) {
                    gameField.getTile()[idx][idx_two].generating(this, number);
                    gameField.getTile()[idx][idx_two].setTileState(TileState.GENERATED);
                }
            }
        }

        if(numberCount > 0){
            generatePlayground();
        }
    }

    private int generateNumber(){
        if(new Random().nextInt(100) <= 5){
            return new Random().nextInt(9) + 1;
        }
        return 0;
    }

    public void fieldOfTile(){
        for(int idx = 0; idx < 9; idx++){
            for(int idx_two = 0; idx_two < 9; idx_two++){
                if(gameField.getTile()[idx][idx_two] == null)
                    gameField.getTile()[idx][idx_two] = new Tile();
            }
        }
    }

    public void generateMarks(){
        for(int row = 0; row < 9; row++){
            for(int collumn = 0; collumn < 9; collumn++){
                gameField.getTile()[row][collumn].setMark(markAreaOne[row][collumn]);
            }
        }
    }

    public int getNumberCount() {
        return numberCount;
    }

    public void setNumberCount(int numberCount) {
        this.numberCount = numberCount;
    }
}