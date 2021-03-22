package sk.tuke.gamestudio.game.jigsaw.core;

import sk.tuke.gamestudio.game.jigsaw.core.gameState.Playing;
import sk.tuke.gamestudio.game.jigsaw.core.gameState.GameState;
import sk.tuke.gamestudio.game.jigsaw.entity.Score;
import sk.tuke.gamestudio.game.jigsaw.service.CommentException;
import sk.tuke.gamestudio.game.jigsaw.service.RatingException;

public class GameField {
    private Tile[][] tile;

    private GameState gameState;

    private Score score;

    public GameField(){
        tile = new Tile[9][9];
        Generate generate = new Generate(this, 10);
        score = new Score("1", "kobistar", 0, new java.util.Date());
        score.setPoints(0);
        generate.fieldOfTile();
        generate.generatePlayground();
        generate.generateMarks();
        gameState = new Playing(this);
    }

    public void action() throws CommentException, RatingException {
        gameState.action();
    }

    public Tile[][] getTile() {
        return tile;
    }
    public void setGameState(GameState gameState){
        this.gameState = gameState;
    }

    public Score getScore(){
        return score;
    }

}