package sk.tuke.gamestudio;

import sk.tuke.gamestudio.game.jigsaw.consoleui.ConsoleUi;
import sk.tuke.gamestudio.game.jigsaw.core.GameField;
import sk.tuke.gamestudio.service.CommentException;
import sk.tuke.gamestudio.service.RatingException;

public class Main {
    public static void main(String[] args) throws CommentException,RatingException {
        GameField field = new GameField();
        ConsoleUi consoleUI = new ConsoleUi(field);
        consoleUI.play();
    }
}



/*
import sk.tuke.gamestudio.game.jigsaw.consoleui.ConsoleUi;
import sk.tuke.gamestudio.game.jigsaw.core.GameField;
import sk.tuke.gamestudio.service.CommentException;
import sk.tuke.gamestudio.service.ScoreServiceJDBC;
import sk.tuke.gamestudio.service.RatingException;
import sk.tuke.gamestudio.service.ScoreService;

public class Main{
    private static ScoreService creatService() {
        return new ScoreServiceJDBC();
    }
    public static void main(String[] args)throws CommentException, RatingException {*/
       /* ScoreService service = creatService();
        service.reset();
        Date date = new Date();
        System.out.println(service.getBestScores("jigsa"));
        Score score = new Score("puzzl","jano",20,date);
        service.addScore(score);
        System.out.println(score);*/
       /* GameField field = new GameField();
        ConsoleUi consoleUI = new ConsoleUi(field);
        consoleUI.play();
*/
       /* try(var connection = DriverManager.getConnection("jdbc:postgresql://localhost/postgres","postgres","awdrg153");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT id, game, username, points, playedon FROM score ORDER BY points DESC LIMIT 10")
        ){
            while(rs.next()){
                System.out.printf("%d %s %s %d %s\n",rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getTimestamp(5));
            }
            //statement.executeUpdate("INSERT INTO score(id, game, username, points, playedon) VALUES('1','jigsaw','jaro',100,'2021-03-11 14:00:14')");
        }*/
  //  }
//}
