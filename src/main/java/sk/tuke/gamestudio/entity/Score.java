package sk.tuke.gamestudio.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@NamedQuery(name = "Score.getBestScores",
        query = "SELECT s FROM Score s WHERE s.game=:game ORDER BY s.points DESC")

public class Score implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String game;
    private String username;
    private int points;
    //@Column("played_on")
    private Date playedOn;
    public Score() {}
/*
    public Score(String game, String username, int points, Date playedOn) {
        this(0, game, username, points, playedOn);
    }
*/
    public Score(String game, String username, int points, Date playedOn) {
        //this.id = id;
        this.game = game;
        this.username = username;
        this.points = points;
        this.playedOn = playedOn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Date getPlayedOn() {
        return playedOn;
    }

    public void setPlayedOn(Date playedOn) {
        this.playedOn = playedOn;
    }

    @Override
    public String toString() {
        return "Score{" +
                "id=" + id +
                ", game='" + game + '\'' +
                ", username='" + username + '\'' +
                ", points=" + points +
                ", playedOn=" + playedOn +
                '}';
    }
/*
    @Override
    public int compareTo(Score o) {
        if(o == null) return -1;
        return this.getPoints() - o.getPoints();
    }*/
}
