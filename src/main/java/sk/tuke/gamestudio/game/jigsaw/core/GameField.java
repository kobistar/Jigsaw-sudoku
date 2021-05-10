package sk.tuke.gamestudio.game.jigsaw.core;

import sk.tuke.gamestudio.entity.Score;
import sk.tuke.gamestudio.game.jigsaw.core.gameState.GameState;
import sk.tuke.gamestudio.game.jigsaw.core.gameState.Playing;
import sk.tuke.gamestudio.service.CommentException;
import sk.tuke.gamestudio.service.RatingException;

public class GameField{
    private Tile[][] tile;
    private GameState gameState;
    private Score score;
    private Generate generate = new Generate(this,50);
    private long startTime = System.currentTimeMillis();

    public GameField(){
        tile = new Tile[9][9];
        generate.fieldOfTile();
        generate.generateNumbers();
        generate.generateMarks();
        gameState = new Playing(this);
    }

    public void action() throws CommentException, RatingException { gameState.action(); }
    public Tile[][] getTile() { return tile; }
    public void setGameState(GameState gameState){ this.gameState = gameState; }
    public GameState getGameState(){return gameState;}
    public int getScore(){ return 100 - (int)(System.currentTimeMillis() - startTime) / 1000; }
}