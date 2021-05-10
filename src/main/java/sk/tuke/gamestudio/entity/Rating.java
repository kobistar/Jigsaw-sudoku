package sk.tuke.gamestudio.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery( name = "Rating.getRating",
                query="SELECT r FROM Rating r WHERE r.game=:game AND r.username=:username"),
        @NamedQuery( name="Rating.getAverageRating",
                query = "SELECT AVG (r.rating) FROM Rating r WHERE r.game =:game")
})
@IdClass(idrating.class)
public class Rating implements Serializable {

    //   @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // private int id;

    @Id
    private String game;
    @Id
    public String username;
    private int rating;
    private Date rated_On;
    public Rating() {}

    public Rating(String game, String username, int rating, Date ratedon) {
        this.username = username;
        this.game = game;
        this.rating = rating;
        this.rated_On = ratedon;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Date getRatedon() {
        return rated_On;
    }

    public void setRatedon(Date ratedon) {
        this.rated_On = ratedon;
    }

    //  public int getId() { return id; }

    // public void setId(int id){ this.id = id;}

    @Override
    public String toString() {
        return "Score{" +
                // "id=" + id +
                ", game='" + game + '\'' +
                ", username='" + username + '\'' +
                ", rating=" + rating +
                ", ratedOn=" + rated_On +
                '}';
    }
}

class idrating implements Serializable {
    private String game;
    private String username;

    public idrating() {
    }

    public idrating(String game, String username) {
        this.game = game;
        this.username = username;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        idrating idrating = (idrating) o;
        return Objects.equals(game, idrating.game) && Objects.equals(username, idrating.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(game, username);
    }
}