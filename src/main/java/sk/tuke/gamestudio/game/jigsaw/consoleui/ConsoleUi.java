package sk.tuke.gamestudio.game.jigsaw.consoleui;

import sk.tuke.gamestudio.game.jigsaw.core.GameField;
import sk.tuke.gamestudio.game.jigsaw.core.ControlGameField;
import sk.tuke.gamestudio.game.jigsaw.core.Input;
import sk.tuke.gamestudio.game.jigsaw.core.gameState.WinnGame;
import java.util.Scanner;

public class ConsoleUi {
    private GameField gameField;
    private ControlGameField controlGameField;

    public ConsoleUi(GameField gameField) {
        this.gameField = gameField;
        controlGameField = new ControlGameField(gameField);
        controlGameField.controlPuzzle();
    }

    public void play() {
        char x,y, n;
        int number,y_2;
        showPlayground(gameField);
        System.out.println();
        do {
            System.out.print("Ur input:");
            String input = new Scanner(System.in).nextLine();
            x = input.charAt(0);
            y = input.charAt(2);
            n = input.charAt(4);
            number = Character.getNumericValue(n);
            y_2 = Character.getNumericValue(y);
            if ((controlGameField.controlInput(toInt(x), y_2, number)) && (controlGameField.insertToPuzzle(toInt(x), y_2, number))) {
                new Input(gameField).inputNumber(toInt(x), y_2, number);
                showPlayground(gameField);
            } else {
                System.out.println("Nemozes sem polozit toto cislo");
            }
        } while (!controlGameField.winGame());
        gameField.setGameState(new WinnGame(gameField));
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

    private void showPlayground(GameField gameField) {
        System.out.print("  ");
        for (int idx = 0; idx < 10; idx++) {
            for (int idx_two = 0; idx_two < 10; idx_two++) {
                if (idx > 0 && idx_two > 0) {
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
                } else if (idx == 0 && idx_two < 9) {
                    System.out.print(idx_two + " ");
                } else if (idx_two == 0) {
                    System.out.print((char) (65 + idx - 1) + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    private int toInt(char c) {
        return (((int) c) - 65);
    }


}


/*    private void printScoreList(List<Score> list){
        for (Score sList : list) {
            System.out.print("Player: " + sList.getPlayer() + ", ");
            System.out.print("Game: " + sList.getGame() + ", ");
            System.out.print("Score: " + sList.getPoints() + ", ");
            System.out.print("Date: " + sList.getPlayedOn());
            System.out.println();
        }
    }

    private void printCommentList(List<Comment> list) {
        for (Comment cList : list) {
            System.out.print("Player: " + cList.getPlayer() + ", ");
            System.out.print("Game: " + cList.getGame() + ", ");
            System.out.print("Comment: " + cList.getComment() + ", ");
            System.out.print("Date: " + cList.getCommentedOn());
            System.out.println();
        }
    }

}*/