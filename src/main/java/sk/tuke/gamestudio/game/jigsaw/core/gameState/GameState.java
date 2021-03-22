package sk.tuke.gamestudio.game.jigsaw.core.gameState;

import sk.tuke.gamestudio.game.jigsaw.service.CommentException;
import sk.tuke.gamestudio.game.jigsaw.service.RatingException;

public interface GameState{
    void action() throws CommentException, RatingException;
}
