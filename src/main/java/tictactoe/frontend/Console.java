package tictactoe.frontend;

import tictactoe.backend.IUltimateTicTacToe;
import tictactoe.controller.MyEvent;
import tictactoe.frontend.util.AnsiColors;
import tictactoe.frontend.util.IBoxUI;
import tictactoe.frontend.util.ITicTacToeBoardRenderer;
import tictactoe.frontend.util.UltimateBoardRenderer;

public class Console implements ITicTacToeUI {
    private final IUltimateTicTacToe game;
    private final ConsoleHelper consoleHelper;
    private String piece;
    private final ITicTacToeBoardRenderer renderer;

    public Console(IUltimateTicTacToe game) {
        this.game = game;
        this.game.addListener(this);
        consoleHelper = new ConsoleHelper();
        piece = "X";
        renderer = new UltimateBoardRenderer(game);
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
        System.out.println(AnsiColors.YELLOW + "\n ----------- TIC TAC TOE 1.0 ----------- \n" + AnsiColors.RESET);
    }

    private void setLabelsMessagesOptionsInit() {
        piece = "X";
        System.out.println(AnsiColors.BLUE + "\n\nChoose a number from 1 to 9 for play" + AnsiColors.RESET);
        System.out.println(AnsiColors.GREEN + "> Press 10 new game \n> Press 11 to the exit game" + AnsiColors.RESET);
        showBoardGame();
        System.out.print("- enter the play number " + piece + " : ");
    }

    private void movePlayer() {
        int number = consoleHelper.enterNumber(81, "play number");
        if (number == 82) {
            game.create();
        } else {
            if (number == 83) {
                System.out.println(consoleHelper.messageFinishGame());
                System.exit(0);
            } else {
                if (!game.markMove(convertRow(number), convertColumn(number))) {
                    System.out.println(AnsiColors.RED + "***play not valid, box already checked" + AnsiColors.RESET);
                    System.out.print("- re-enter the play number: ");
                    movePlayer();
                }
            }
        }
    }

    private void checkStatusGame() {
        if (game.checkTicTacToe()) {
            String winner = String.valueOf(game.winner());
            System.out.println(consoleHelper.messageWinnerGame(winner) + "\n");
            starSubMenu();
        } else {
            if (game.draw()) {
                System.out.println(consoleHelper.messageDrawGame() + "\n");
                starSubMenu();
            } else {
                System.out.print("- enter the play number " + piece + " : ");
            }
        }
    }

    private void starSubMenu() {
        System.out.println(AnsiColors.GREEN + "> Press 10 new game \n> Press 11 to the exit game" + AnsiColors.RESET + "\n");
        System.out.print("- enter the option: ");
    }

    private void showBoardGame() {
        var board = renderer.getRenderableBoard();
        var getCool = game.getEnabledQuadrant();
        System.out.println(getCool.getX() + "," + getCool.getY());
        System.out.println();
        for (int i = 0; i < board.length; i++) {
            IBoxUI[] chars = board[i];
            for (int j = 0; j < board.length; j++) {
                IBoxUI currentBox = chars[j];
                String boxColor = currentBox.getBoxColor();
                String symbolColor = currentBox.getSymbolColor();
                System.out.print(boxColor + "[ ");
                System.out.print(symbolColor + currentBox.getSymbol());
                System.out.print(boxColor + " ]" + AnsiColors.RESET);

            }
            System.out.println();
        }
        System.out.println();

        getCool = game.getEnabledQuadrant();
        System.out.println(getCool.getX() + "," + getCool.getY());
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
