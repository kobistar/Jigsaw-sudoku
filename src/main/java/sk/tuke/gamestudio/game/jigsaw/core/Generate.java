package sk.tuke.gamestudio.game.jigsaw.core;

import static sk.tuke.gamestudio.game.jigsaw.core.models.JigsawModels.jigsaw1;
import static sk.tuke.gamestudio.game.jigsaw.core.models.NumbersModels.numbers1;

public class Generate {
    private int numberCount;
    private GameField gameField;
    private ControlGameField controlGameField;

    public Generate(GameField gameField, int numberCount){
        this.numberCount = numberCount;
        this.gameField = gameField;

        controlGameField = new ControlGameField(gameField);
    }

 /*   public void generatePlayground(){
        int number;
        for(int idx = 0; idx < 9; idx++){
            for(int idx_two = 0; idx_two < 9; idx_two++){
                number = generateNumber();
                if(new ControlGameField(gameField).controlInput(idx, idx_two, number)){
                    gameField.getTile()[idx][idx_two].generating(this, number);
                    gameField.getTile()[idx][idx_two].setTileState(TileState.GENERATED);
                }
            }
        }
        if(numberCount > 0){
            generatePlayground();
        }
    }*/
/*
    private int generateNumber(){
        if(new Random().nextInt(100) <= 5){
            return new Random().nextInt(9) + 1;
        }
        return 0;
    }
*/
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
                gameField.getTile()[row][collumn].setMark(jigsaw1[row][collumn]);
            }
        }
    }
    void generateNumbers(){
        for(int row = 0; row < 9; row++){
            for(int collumn = 0; collumn < 9; collumn++){
                gameField.getTile()[row][collumn].setNumber(numbers1[row][collumn], getClass());
            }
        }
    }

    public int getNumberCount() {
        return numberCount;
    }

    public void setNumberCount(int numberCount) {
        this.numberCount = numberCount;
    }
    /*
     private String chooseJigsaw(){
//        new Random().nextInt(5)+1;
//        String.format("jigsaw%d")
        return "";
    }
     */
}
