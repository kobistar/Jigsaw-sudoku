package sk.tuke.gamestudio.game.jigsaw.core.gameState;

import sk.tuke.gamestudio.game.jigsaw.core.ControlGameField;
import sk.tuke.gamestudio.game.jigsaw.core.GameField;
import sk.tuke.gamestudio.game.jigsaw.core.Input;

import javax.validation.constraints.NotNull;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Playing implements GameState {
    private static final Pattern INPUT_PATTERN = Pattern.compile("([A-I])([A-I])([1-9])");
    private Scanner scanner = new Scanner(System.in);
    private GameField gameField;
    private ControlGameField controlGameField;

    public Playing(GameField gameField){
        this.gameField = gameField;
        controlGameField = new ControlGameField(gameField);
        controlGameField.controlPuzzle();
    }


    @Override
    public void action()  {
        showPlayground();
        do{
            handleInput();
        }while (!controlGameField.winGame());
    }

    private void handleInput(){
        System.out.print("Ur input:");
        String input = scanner.nextLine().toUpperCase().trim();

        Matcher matcher = INPUT_PATTERN.matcher(input);

        if("Q".equals(input))
            System.exit(0);

        if(matcher.matches()) {
            if (controlInputs(input)) {
                input(input);
                showPlayground();
            } else {
                System.out.println("U cant input this number to this place");
            }
        }else{
            System.out.println("Wrong input");
        }
    }

    private void input(@NotNull String input){
        new Input(gameField).inputNumber(toInt(input.charAt(0)),toInt(input.charAt(1)), Character.getNumericValue(input.charAt(2)));
        ///addScore(((totalInputs-wrongInputs)*(totalInputs-wrongInputs)));
    }

    private boolean controlInputs(@NotNull String input){
        return (controlGameField.controlInput(toInt(input.charAt(0)), toInt(input.charAt(1)), Character.getNumericValue(input.charAt(2))))
                && (controlGameField.insertToPuzzle(toInt(input.charAt(0)), toInt(input.charAt(1)), Character.getNumericValue(input.charAt(2))));
    }

    private static final String ANSI_RESET = "\033[0m";
    private static final String ANSI_RED_BACKGROUND = "\033[41m";
    private static final String ANSI_GREEN_BACKGROUND = "\033[42m";
    private static final String ANSI_YELLOW_BACKGROUND = "\033[43m";
    private static final String ANSI_BLUE_BACKGROUND = ("\033[44m");
    private static final String ANSI_MAGENTA_BACKGROUND = ("\033[45m");
    private static final String ANSI_CYAN_BACKGROUND = ("\033[46m");
    private static final String ANSI_GREY_BACKGROUND = ("\033[47m");
    private static final String ANSI_ORANGE_BACKGROUND = "\033[48;2;225;153;0m";
    private static final String ANSI_BLACK_BACKGROUND = ("\033[40m");

    private void showPlayground(){
        System.out.println("Score: " + gameField.getScore());
        System.out.print("  ");
        for(int idx = 0; idx < 10; idx++){
            for(int idx_two = 0; idx_two < 10; idx_two++){
                if(idx > 0 && idx_two > 0)
                    if (gameField.getTile()[idx - 1][idx_two - 1].getMark() == 1)
                        System.out.print(ANSI_RED_BACKGROUND + gameField.getTile()[idx - 1][idx_two - 1].getNumber() + ANSI_RESET + " ");
                    else if (gameField.getTile()[idx - 1][idx_two - 1].getMark() == 2)
                        System.out.print(ANSI_GREEN_BACKGROUND + gameField.getTile()[idx - 1][idx_two - 1].getNumber() + ANSI_RESET + " ");
                    else if (gameField.getTile()[idx - 1][idx_two - 1].getMark() == 3)
                        System.out.print(ANSI_YELLOW_BACKGROUND + gameField.getTile()[idx - 1][idx_two - 1].getNumber() + ANSI_RESET + " ");
                    else if (gameField.getTile()[idx - 1][idx_two - 1].getMark() == 4)
                        System.out.print(ANSI_BLUE_BACKGROUND + gameField.getTile()[idx - 1][idx_two - 1].getNumber() + ANSI_RESET + " ");
                    else if (gameField.getTile()[idx - 1][idx_two - 1].getMark() == 5)
                        System.out.print(ANSI_MAGENTA_BACKGROUND + gameField.getTile()[idx - 1][idx_two - 1].getNumber() + ANSI_RESET + " ");
                    else if (gameField.getTile()[idx - 1][idx_two - 1].getMark() == 6)
                        System.out.print(ANSI_CYAN_BACKGROUND + gameField.getTile()[idx - 1][idx_two - 1].getNumber() + ANSI_RESET + " ");
                    else if (gameField.getTile()[idx - 1][idx_two - 1].getMark() == 7)
                        System.out.print(ANSI_GREY_BACKGROUND + gameField.getTile()[idx - 1][idx_two - 1].getNumber() + ANSI_RESET + " ");
                    else if (gameField.getTile()[idx - 1][idx_two - 1].getMark() == 8)
                        System.out.print(ANSI_ORANGE_BACKGROUND + gameField.getTile()[idx - 1][idx_two - 1].getNumber() + ANSI_RESET + " ");
                    else if (gameField.getTile()[idx - 1][idx_two - 1].getMark() == 9)
                        System.out.print(ANSI_BLACK_BACKGROUND + gameField.getTile()[idx - 1][idx_two - 1].getNumber() + ANSI_RESET + " ");

                    else {
                        System.out.print(gameField.getTile()[idx - 1][idx_two - 1].getNumber() + " ");
                    }
                else if(idx == 0 && idx_two < 9){
                    System.out.print((char)(65 + idx_two) + " ");
                }else if(idx_two == 0){
                    System.out.print((char)(65 + idx - 1) + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    private int toInt(char c){
        return  (((int)c) - 65);
    }

}
