package sk.tuke.gamestudio.game.jigsaw.consoleui;

import org.springframework.beans.factory.annotation.Autowired;
import sk.tuke.gamestudio.entity.Comment;
import sk.tuke.gamestudio.entity.Rating;
import sk.tuke.gamestudio.entity.Score;
import sk.tuke.gamestudio.game.jigsaw.core.GameField;
import sk.tuke.gamestudio.service.*;

import java.util.Date;
import java.util.List;
import java.util.Scanner;


public class ConsoleUi {
    private static final String GAME_NAME = "jigsaw";
    private final GameField gameField;

    @Autowired
    private  ScoreService scoreService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private RatingService ratingService;

    private final Scanner scanner = new Scanner(System.in);

    public ConsoleUi(GameField gameField) {
        this.gameField = gameField;

    }

    public void play() throws CommentException, RatingException {

        gameField.action();
        String name;
        String comment;
        int rating;
        System.out.println("CG! U are winner!");
        System.out.print("Ur name: ");
        name = scanner.nextLine().toUpperCase().trim();
        scoreService.addScore(new Score(GAME_NAME, name, gameField.getScore(), new Date()));

        System.out.print("Ur comment: ");
        comment = scanner.nextLine();
        commentService.addComment(new Comment(GAME_NAME, name, comment, new Date()));

        System.out.print("Ur rating(1-5): ");
        rating = scanner.nextInt();
        while ((rating > 5 || rating < 1)){
            System.out.println("Wrong input, try again");
            System.out.print("Ur rating(1-5): ");
            rating = scanner.nextInt();
        }

        ratingService.setRating(new Rating(GAME_NAME, name,rating, new Date()));
        List<Score> scoreList = scoreService.getBestScores(GAME_NAME);
        List<Comment> commentList = commentService.getComments(GAME_NAME);

        System.out.println("\nScoreList\n");
        printScoreList(scoreList);
        System.out.println("\nCommentList\n");
        printCommentList(commentList);
    }

    private void printScoreList(List<Score> list){
        for (Score sList : list) {
            System.out.print("Player: " + sList.getUsername() + ", ");
            System.out.print("Game: " + sList.getGame() + ", ");
            System.out.print("Score: " + sList.getPoints() + ", ");
            System.out.print("Date: " + sList.getPlayedOn());
            System.out.println();
        }
    }

    private void printCommentList(List<Comment> list) {
        for (Comment cList : list) {
            System.out.print("Player: " + cList.getUsername() + ", ");
            System.out.print("Game: " + cList.getGame() + ", ");
            System.out.print("Comment: " + cList.getComment() + ", ");
            System.out.print("Date: " + cList.getCommentedOn());
            System.out.println();
        }
    }

    private String insertInfo(String string) {
        if (string == null) {
            throw new NullPointerException();
        }
        return string;
    }
}