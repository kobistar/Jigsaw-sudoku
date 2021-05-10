package sk.tuke.gamestudio.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import sk.tuke.gamestudio.entity.Comment;
import sk.tuke.gamestudio.entity.Rating;
import sk.tuke.gamestudio.entity.Score;
import sk.tuke.gamestudio.game.jigsaw.core.ControlGameField;
import sk.tuke.gamestudio.game.jigsaw.core.GameField;
import sk.tuke.gamestudio.game.jigsaw.core.Input;
import sk.tuke.gamestudio.game.jigsaw.core.gameState.Playing;
import sk.tuke.gamestudio.service.*;

import java.util.Date;

import static sk.tuke.gamestudio.game.jigsaw.core.models.NumbersModels.numbers1;

//http://localhost:8080/jigsaw
@Controller
@RequestMapping("/jigsaw")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class JigSawController {

    @Autowired
    protected UserController userController;

    @Autowired
    protected ScoreService scoreService;

    @Autowired
    protected CommentService commentService;

    @Autowired
    protected RatingService ratingService;

    private GameField gameField = new GameField();
    private int totalInputs= 0, correctInputs = 1;
    private boolean numOne,numTwo,numThree,numFour,numFive,numSix,numSeven,numEight,numNine,score;//winn,gum;
    private ControlGameField controlGameField;
    @RequestMapping
    public String jigsaw(@RequestParam(required = false) Integer row,
                         @RequestParam(required = false) Integer column, Model model) throws CommentException, RatingException{
        try{
            if(gameField.getGameState() instanceof Playing) {
                int number = chooseNumber();
                totalInputs++;
                if ((((Playing) gameField.getGameState()).getControlGameField().controlInput(row, column, number)) && (((Playing) gameField.getGameState()).getControlGameField().insertToPuzzle(row, column, number))) {
                    new Input(gameField).inputNumber(row, column, number);
                    correctInputs++;
                } else ; //zly input;

            }
            //if(gum) new Input(gameField).inputNumber(row,column,0);
        }
        catch (Exception e){
            //zlý input
        }
        if(win() && userController.isLogged()){
            int points = 100-(2 * correctInputs * (correctInputs / totalInputs));
            scoreService.addScore(new Score("jigsaw", userController.getLoggedUser().getLogin(), points, new Date()));
        }
        ModelAdd(model);
        return "jigsaw";
    }

    @RequestMapping("/addComment")
    public String addComment (@RequestParam(name = "commentString", required = false) String commentString, Model model) throws CommentException, RatingException{
        try {
            if((commentString != null) && (commentString.length() > 1)){
                commentService.addComment(new Comment("jigsaw", userController.getLoggedUser().getLogin(), commentString, new java.util.Date()));
            }
        }catch (CommentException e){
            e.printStackTrace();
        }

        return "jigsaw";
    }

    @RequestMapping(value = "/addRating")
    public String addRating(@RequestParam(value = "rating", required = false) String ratingString, Model model) throws CommentException, RatingException {
        try {
            if(ratingString != null){
                ratingService.setRating(new Rating("jigsaw", userController.getLoggedUser().getLogin(), Integer.parseInt(ratingString), new java.util.Date()));
            }
        } catch (RatingException e) {
            e.printStackTrace();
        }

        return "jigsaw";
    }

    @RequestMapping("/score")
    public String Score(){
        score = !score;
        return "jigsaw";
    }
    @RequestMapping("/again")
    public String NewGame(){
        gameField = new GameField();
        return "jigsaw";
    }

    @RequestMapping("/numberOne")
    public String NumberOne(){
        numOne = !numOne;
        return "jigsaw";
    }
    @RequestMapping("/numberTwo")
    public String NumberTwo(){
        numTwo = !numTwo;
        return "jigsaw";
    }
    @RequestMapping("/numberThree")
    public String NumberThree(){
        numThree = !numThree;
        return "jigsaw";
    }
    @RequestMapping("/numberFour")
    public String NumberFour(){
        numFour = !numFour;
        return "jigsaw";
    }
    @RequestMapping("/numberFive")
    public String NumberFive(){
        numFive = !numFive;
        return "jigsaw";
    }
    @RequestMapping("/numberSix")
    public String NumberSix(){
        numSix = !numSix;
        return "jigsaw";
    }
    @RequestMapping("/numberSeven")
    public String NumberSeven(){
        numSeven = !numSeven;
        return "jigsaw";
    }
    @RequestMapping("/numberEight")
    public String NumberEight(){
        numEight = !numEight;
        return "jigsaw";
    }
    @RequestMapping("/numberNine")
    public String NumberNine(){
        numNine = !numNine;
        return "jigsaw";
    }

    public boolean isNumOne(){return numOne;}
    public boolean isNumTwo(){return numTwo;}
    public boolean isNumThree(){return numThree;}
    public boolean isNumFour(){return numFour;}
    public boolean isNumFive(){return numFive;}
    public boolean isNumSix(){return numSix;}
    public boolean isNumSeven(){return numSeven;}
    public boolean isNumEight(){return numEight;}
    public boolean isNumNine(){return numNine;}
    //public boolean isGum(){return gum;}

    public String GetHtmlField() {
        StringBuilder sb = new StringBuilder();
        sb.append("<table>\n");

        for(int row = 0; row < 9; row++){
            sb.append("<tr>\n");
            for(int column = 0; column < 9; column++){
                sb.append("<td>");
                if(numbers1[row][column] == 0 && GetNumberName(row,column) == 0) sb.append(String.format("<a href = '/jigsaw?row=%d&column=%d'>\n",row,column));
                sb.append("<img src ='/images/" + GetNumberName(row,column) +"."+ GetModel(row,column) + "Tile.png'>");
                sb.append("</a>\n");
                sb.append("</td>\n");
            }
            sb.append("</tr>\n");
        }
        sb.append("</table>\n");
        return sb.toString();

    }

    private int chooseNumber(){
        if(numOne) return 1;
        if(numTwo) return 2;
        if(numThree) return 3;
        if(numFour) return 4;
        if(numFive) return 5;
        if(numSix) return 6;
        if(numSeven) return 7;
        if(numEight) return 8;
        return 9;
    }

    void ModelAdd(Model model) throws CommentException,RatingException{
        model.addAttribute("scores", scoreService.getBestScores("jigsaw"));
        model.addAttribute("comment",commentService.getComments("jigsaw"));
        model.addAttribute("rating",ratingService.getAverageRating("jigsaw"));
        if(userController.getLoggedUser() != null) {
            model.addAttribute("ratingPlayer", ratingService.getRating("jigsaw", userController.getLoggedUser().getLogin()));
        }
    }

    private int GetNumberName(int row, int column){ return gameField.getTile()[row][column].getNumber(); }
    private int GetModel(int row, int column){ return gameField.getTile()[row][column].getMark(); }

    private boolean win(){
        for(int x = 0; x < 9; x++){
            for(int y = 0; y < 9;y++){
                if(GetNumberName(x,y) == 0){
                    return false;
                }
            }
        }
        return true;
    }
}



