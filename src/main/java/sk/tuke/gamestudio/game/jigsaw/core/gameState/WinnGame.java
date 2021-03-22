
package sk.tuke.gamestudio.game.jigsaw.core.gameState;

import sk.tuke.gamestudio.game.jigsaw.core.GameField;
import sk.tuke.gamestudio.game.jigsaw.entity.Comment;
import sk.tuke.gamestudio.game.jigsaw.entity.Rating;
import sk.tuke.gamestudio.game.jigsaw.entity.Score;
import sk.tuke.gamestudio.game.jigsaw.service.*;

import java.util.Scanner;
public class WinnGame implements GameState {
    private static final String GAME_NAME = "jigsaw";

    private GameField gameField;

    private Scanner scanner = new Scanner(System.in);

    private ScoreService scoreService;

    private RatingService ratingService;

    private CommentService commentService;

    public WinnGame(GameField gameField, ScoreService scoreService, RatingService ratingService, CommentService commentService){
        this.gameField = gameField;
        this.scoreService = scoreService;
        this.ratingService = ratingService;
        this.commentService = commentService;
    }

    public WinnGame(GameField gameField){
        this.gameField = gameField;
    }
    @Override
    public void action() throws CommentException, RatingException {
        String name;
        System.out.println();
        System.out.println("CG! U are winner!");
        System.out.print("Ur name: ");
        name = scanner.nextLine().toUpperCase().trim();
        System.out.println(scoreService);
        scoreService.addScore(new Score(GAME_NAME, "Simon", 10, new java.util.Date()));
        System.out.println();
        System.out.print("Ur comment: ");
//        scanner.nextLine().toUpperCase().trim();
        commentService.addComment(new Comment(GAME_NAME, name, insertInfo(scanner.nextLine().toUpperCase().trim()), new java.util.Date()));
        System.out.println();
        System.out.print("Ur rating: ");
        ratingService.setRating(new Rating(GAME_NAME, name, insertInfo(scanner.nextLine().toUpperCase().trim()), new java.util.Date()));
    }

    private String insertInfo(String string){
        if(string == null){
            throw new NullPointerException();
        }
        return string;
    }
}
