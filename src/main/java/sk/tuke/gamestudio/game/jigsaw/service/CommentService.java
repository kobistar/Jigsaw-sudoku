package sk.tuke.gamestudio.game.jigsaw.service;
import sk.tuke.gamestudio.game.jigsaw.entity.Comment;
import java.util.List;

public interface CommentService {
    void addComment(Comment comment) throws CommentException;
    List<Comment> getComments(String game) throws CommentException;
}
