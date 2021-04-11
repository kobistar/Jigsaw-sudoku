package sk.tuke.gamestudio.service;

import sk.tuke.gamestudio.entity.Rating;

import java.sql.*;

/*
    CREATE TABLE rating (
        player VARCHAR(64) NOT NULL,
        game VARCHAR(64) NOT NULL,
        rating INTEGER NOT NULL,
        ratedon TIMESTAMP NOT NULL
    );
     */
public class RatingServiceJDBC implements RatingService {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "awdrg153";
    private static final String INSERT_RATING = "INSERT INTO rating (game,username, rating, rated_on) VALUES (?, ?, ?, ?)";
    private static final String SELECT_RATING = "SELECT rating FROM rating WHERE game = ? AND username = ? ORDER BY rating DESC LIMIT 1";
    private static final String SELECT_AVERAGE = "SELECT AVG (rating) FROM rating WHERE game = ?";

    @Override
    public void setRating(Rating rating) throws RatingException {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            try(PreparedStatement ps = connection.prepareStatement(INSERT_RATING)){
                ps.setString(1, rating.getGame());
                ps.setString(2, rating.getUsername());
                ps.setInt(3, rating.getRating());
                ps.setDate(4, new Date(rating.getRatedon().getTime()));

                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RatingException("Error saving rating", e);
        }

    }

    @Override
    public int getAverageRating(String game) throws RatingException {
        int average;
        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)){
            try(PreparedStatement ps = connection.prepareStatement(SELECT_AVERAGE)){
                ps.setString(1, game);

                ResultSet rs = ps.executeQuery();
                rs.next();
                average = rs.getInt(1);
            }
        }catch (SQLException e){
            throw new RatingException("Error average rating", e);
        }
        return average;
    }

    @Override
    public int getRating(String game, String player) throws RatingException {
        int rating;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            try(PreparedStatement ps = connection.prepareStatement(SELECT_RATING)){
                ps.setString(1, game);
                ps.setString(2, player);

                ResultSet rs = ps.executeQuery();
                rs.next();
                rating = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RatingException("Error loading rating", e);
        }
        return rating;
    }
}
