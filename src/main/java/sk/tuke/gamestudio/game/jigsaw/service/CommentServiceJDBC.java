package sk.tuke.gamestudio.game.jigsaw.service;
import sk.tuke.gamestudio.game.jigsaw.entity.Comment;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/*
    CREATE TABLE comment (
        player VARCHAR(64) NOT NULL,
        game VARCHAR(64) NOT NULL,
        comment VARCHAR(255) NOT NULL,
        commentedon TIMESTAMP NOT NULL
    );
     */

public class CommentServiceJDBC implements CommentService{
    private static final String URL = "jdbc:postgresql://localhost:5432/gamestudio-4978";
    private static final String USER = "postgres";
    private static final String PASSWORD = "awdrg153";

    private static final String INSERT_SCORE = "INSERT INTO comment (game, player, comment, commentedon) VALUES (?, ?, ?, ?)";
    private static final String SELECT_COMMENT = "SELECT game, player, comment, commentedon FROM comment Where game = ? ORDER BY commentedon DESC LIMIT 10";

    @Override
    public void addComment(Comment comment) throws CommentException {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            try(PreparedStatement ps = connection.prepareStatement(INSERT_SCORE)){
                ps.setString(1, comment.getGame());
                ps.setString(2, comment.getPlayer());
                ps.setString(3, comment.getComment());
                ps.setDate(4, new Date(comment.getCommentedOn().getTime()));

                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new CommentException("Error saving comment", e);
        }
    }

    @Override
    public List<Comment> getComments(String game) throws CommentException {
        List<Comment> comments = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            try(PreparedStatement ps = connection.prepareStatement(SELECT_COMMENT)){
                ps.setString(1, game);
                try(ResultSet rs = ps.executeQuery()) {
                    while(rs.next()) {
                        Comment comment = new Comment(
                                rs.getString(1),
                                rs.getString(2),
                                rs.getString(3),
                                rs.getTimestamp(4)
                        );
                        comments.add(comment);
                    }
                }
            }
        } catch (SQLException e) {
            throw new CommentException("Error loading comment", e);
        }
        return comments;
    }
}