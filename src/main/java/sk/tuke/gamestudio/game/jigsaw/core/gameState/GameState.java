package sk.tuke.gamestudio.game.jigsaw.core.gameState;


import sk.tuke.gamestudio.service.CommentException;
import sk.tuke.gamestudio.service.RatingException;

public interface GameState {
    void action() throws CommentException, RatingException;
    //pp
}
