package sk.tuke.gamestudio.game.jigsaw;
import sk.tuke.gamestudio.game.jigsaw.consoleui.ConsoleUi;
import sk.tuke.gamestudio.game.jigsaw.core.GameField;
import sk.tuke.gamestudio.game.jigsaw.service.CommentException;
import sk.tuke.gamestudio.game.jigsaw.service.RatingException;

public class jigsaw {
    public static void main(String[] args)  throws CommentException, RatingException {
        GameField gameField = new GameField();
        System.out.println();
        System.out.println("U can input the number to Game Field with this syntax...");
        System.out.println("Position Row, Position Collumn, Number whith u can input");
        System.out.println("Example: A,1,5");
        System.out.println();

        gameField.action();
    }
}

