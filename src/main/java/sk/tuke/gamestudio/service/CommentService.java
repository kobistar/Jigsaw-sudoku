package sk.tuke.gamestudio.service;

import sk.tuke.gamestudio.entity.Comment;
import java.util.List;

public interface CommentService {
    //pp
    void addComment(Comment comment) throws CommentException;
    List<Comment> getComments(String game) throws CommentException;
}
