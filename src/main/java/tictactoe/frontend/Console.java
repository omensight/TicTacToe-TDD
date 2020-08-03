package tictactoe.frontend;

import tictactoe.backend.ITicTacToe;
import tictactoe.backend.IUltimateTicTacToe;
import tictactoe.controller.MyEvent;

public class Console implements ITicTacToeUI {
    private final IUltimateTicTacToe game;
    private final Helper helper;
    private String piece;

    public Console(IUltimateTicTacToe game) {
        this.game = game;
        this.game.addListener(this);
        helper = new Helper();
        piece = "X";
    }

    @Override
    public void run() {
        setLabelsGameInit();
        play();
    }

    @Override
    public void update(MyEvent event) {
        if (event.getPropertyName().equals("markMove")) {
            changeLabelTurn();
            showBoardGame();
            checkStatusGame();
        }
        if (event.getPropertyName().equals("create")) {
            setLabelsMessagesOptionsInit();
        }

    }

    private void play() {
        setLabelsMessagesOptionsInit();
        while (true) {
            movePlayer();
        }
    }

    private void setLabelsGameInit() {
        System.out.println();
        System.out.println(helper.colorYellow() + "\n ----------- TIC TAC TOE 1.0 ----------- \n" + helper.resetColor());
    }

    private void setLabelsMessagesOptionsInit() {
        piece = "X";
        System.out.println(helper.colorBlue() + "\n\nChoose a number from 1 to 9 for play" + helper.resetColor());
        System.out.println(helper.colorGreen() + "> Press 10 new game \n> Press 11 to the exit game" + helper.resetColor());
        showBoardGame();
        System.out.print("- enter the play number " + piece + " : ");
    }

    private void movePlayer() {
        int number = helper.enterNumber(81, "play number");
        if (number == 82) {
            game.create();
        } else {
            if (number == 83) {
                System.out.println(helper.messageFinishGame());
                System.exit(0);
            } else {
                if (!game.markMove(convertRow(number), convertColumn(number))) {
                    System.out.println(helper.colorRed() + "***play not valid, box already checked" + helper.resetColor());
                    System.out.print("- re-enter the play number: ");
                    movePlayer();
                }
            }
        }
    }

    private void checkStatusGame() {
        if (game.checkTicTacToe()) {
            String winner = String.valueOf(game.winner());
            System.out.println(helper.messageWinnerGame(winner) + "\n");
            starSubMenu();
        } else {
            if (game.draw()) {
                System.out.println(helper.messageDrawGame() + "\n");
                starSubMenu();
            } else {
                System.out.print("- enter the play number " + piece + " : ");
            }
        }
    }

    private void starSubMenu() {
        System.out.println(helper.colorGreen() + "> Press 10 new game \n> Press 11 to the exit game" + helper.resetColor() + "\n");
        System.out.print("- enter the option: ");
    }

    private void showBoardGame() {
        char[][] boardPlay = game.getBoard();
        char[][] globalBoard = game.getGlobalBoard();
        var enabledQuadrant = game.getEnabledQuadrant();
        System.out.println();
        for (int i = 0; i < boardPlay.length; i++) {
            char[] chars = boardPlay[i];
            for (int j = 0; j < boardPlay.length; j++) {
                char box = chars[j];
                if (box == 0){
                    box = ' ';
                }
                String boxColor = box == 'X' ? helper.colorPurple() : helper.colorCyan();
                System.out.print("[ ");
                System.out.print(boxColor + box);
                System.out.print(helper.resetColor() + " ]");

            }
            System.out.println();
        }
        System.out.println();
    }

    private void changeLabelTurn() {
        if (piece.equals("X")) {
            piece = "O";
        } else {
            piece = "X";
        }
    }

    private int convertRow(int number) {
        return (number - 1) / 9;
    }

    private int convertColumn(int number) {
        return (number - 1) % 9;
    }
}
