package sk.tuke.gamestudio.game.jigsaw.core.gameState;

import sk.tuke.gamestudio.game.jigsaw.consoleui.ConsoleUi;
import sk.tuke.gamestudio.game.jigsaw.core.GameField;


public class Playing implements GameState {
       private GameField gameField;

    public Playing(GameField gameField){
        this.gameField = gameField;
    }

    @Override
    public void action() {
        new ConsoleUi(gameField).play();
    }
}