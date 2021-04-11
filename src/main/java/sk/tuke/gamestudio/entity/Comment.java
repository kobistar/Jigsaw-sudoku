package sk.tuke.gamestudio.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@NamedQuery( name = "Comment.getComment",
        query = "SELECT c FROM Comment c WHERE c.game=:game")

public class Comment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String game;
    private String username;
    private String comment;
    private Date commented_On;
    public Comment(){}
/*
    public Comment(String username, String comment, Date commentedOn){
        this(game, username,comment,commentedOn);
    }
*/
    public Comment(String game, String username, String comment, Date commentedOn) {
        //this.id = id;
        this.username = username;
        this.game = game;
        this.comment = comment;
        this.commented_On = commentedOn;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCommentedOn() {
        return commented_On;
    }

    public void setCommentedOn(Date commentedOn) {
        this.commented_On = commentedOn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", game='" + game + '\'' +
                ", username='" + username + '\'' +
                ", comment=" + comment +
                ", commentedOn=" + commented_On +
                '}';
    }
}
