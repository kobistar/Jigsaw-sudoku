package sk.tuke.gamestudio.game.jigsaw.service;
import sk.tuke.gamestudio.game.jigsaw.entity.Score;
import java.util.List;
import java.sql.SQLException;

public interface ScoreService {
    void addScore(Score score) /*throws ScoreException, ClassNotFoundException, SQLException*/;
    List<Score> getBestScores(String game) throws ScoreException;
    void reset() throws ScoreException;
}
