package sk.tuke.gamestudio.game.jigsaw.service;

public class ScoreException extends RuntimeException{
    public ScoreException(String message) {
        super(message);
    }

    public ScoreException(String message, Throwable cause) {
        super(message, cause);
    }

}
