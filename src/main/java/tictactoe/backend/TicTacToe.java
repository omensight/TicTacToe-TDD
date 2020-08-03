package tictactoe.backend;

import tictactoe.controller.ITurnHandler;
import tictactoe.controller.TwoPlayerTurnHandler;
import tictactoe.frontend.ITicTacToeUI;
import tictactoe.controller.MyEvent;

import java.util.ArrayList;
import java.util.List;

public class TicTacToe implements ITicTacToe {
    private final char[][] board;
    private final int dimension;
    private final List<ITicTacToeUI> support;
    private int numberMove;
//    private char turn;
    private char winner;
    private final ITurnHandler<Character> turnHandler;
    private final boolean ableToChangeTurn;
    private final static int MIN_WINNER_MOVES = 3;

    public TicTacToe(ITurnHandler<Character> turnHandler) {
        this.turnHandler = turnHandler;
        ableToChangeTurn = false;
        dimension = 3;
        board = new char[dimension][dimension];
        support = new ArrayList<>();
        create();
    }

    public TicTacToe() {
        this.turnHandler = new TwoPlayerTurnHandler<>('X', 'O');
        ableToChangeTurn = true;
        dimension = 3;
        board = new char[dimension][dimension];
        support = new ArrayList<>();
        create();
    }
    @Override
    public void create() {
        numberMove = 0;
        winner = '\0';
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                board[i][j] = '\0';
            }
        }
        notifyListener(new MyEvent(this, "create", false, true));
    }

    @Override
    public boolean markMove(int row, int column) {
        boolean mark = false;
        if (!checkTicTacToe()) {
            if (checkLimits(row) && checkLimits(column)) {
                if (markBox(row, column)) {
                    mark = true;
                    numberMove++;
                    changeTurn();
                    notifyListener(new MyEvent(this, "markMove", false, true));
                }
            }
        }
        return mark;
    }

    @Override
    public boolean checkTicTacToe() {
        boolean thereIs = false;
        if (numberMove >= MIN_WINNER_MOVES) {
            if (rowWinner() || columnWinner() || diagonalWinner()) {
                thereIs = true;
                notifyListener(new MyEvent(this, "winner", false, true));
            }
        }
        return thereIs;
    }

    @Override
    public char winner() {
        return winner;
    }

    @Override
    public boolean draw() {
        boolean is = false;
        if (finish() && winner == '\0') {
            is = true;
        }
        return is;
    }

    @Override
    public char[][] getBoard() {
        char[][] boardShape = new char[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            System.arraycopy(board[i], 0, boardShape[i], 0, dimension);
        }
        return boardShape;
    }

    private boolean checkLimits(int value) {
        boolean check = false;
        int limitMin = 0;
        int limitMax = 3;
        if (value >= limitMin && value < limitMax) {
            check = true;
        }
        return check;
    }

    private boolean markBox(int row, int column) {
        boolean mark = false;
        if (checkBox(row, column)) {
            board[row][column] = turnHandler.getTurn();
            mark = true;
        }
        return mark;
    }

    private boolean checkBox(int row, int column) {
        boolean status = false;
        if ('\0' == board[row][column]) {
            status = true;
        }
        return status;
    }

    private void changeTurn() {
        if (ableToChangeTurn){
            turnHandler.changeTurn();
        }
    }

    private boolean rowWinner() {
        boolean earner = false;
        int counterX;
        int counterO;
        for (int i = 0; i < dimension; i++) {
            counterX = 0;
            counterO = 0;
            for (int j = 0; j < dimension; j++) {
                if (board[i][j] == 'X') {
                    counterX++;
                }
                if (board[i][j] == 'O') {
                    counterO++;
                }
            }
            if (counterX == 3 || counterO == 3) {
                getWinner(counterX, counterO);
                earner = true;
            }
        }
        return earner;
    }

    private boolean columnWinner() {
        boolean earner = false;
        int counterX;
        int counterO;
        for (int i = 0; i < dimension; i++) {
            counterX = 0;
            counterO = 0;
            for (int j = 0; j < dimension; j++) {
                if (board[j][i] == 'X') {
                    counterX++;
                }
                if (board[j][i] == 'O') {
                    counterO++;
                }
            }
            if (counterX == 3 || counterO == 3) {
                getWinner(counterX, counterO);
                earner = true;
            }
        }
        return earner;
    }

    private boolean diagonalWinner() {
        boolean earner = false;
        int counterX = 0;
        int counterO = 0;
        for (int i = 0; i < 3; i++) {
            if (board[i][i] == 'X') {
                counterX++;
            }
            if (board[i][i] == 'O') {
                counterO++;
            }
        }
        if (counterX == 3 || counterO == 3) {
            getWinner(counterX, counterO);
            earner = true;
        }
        counterX = 0;
        counterO = 0;
        for (int i = 2; i >= 0; i--) {
            if (board[i][2 - i] == 'X') {
                counterX++;
            }
            if (board[i][2 - i] == 'O') {
                counterO++;
            }
        }
        if (counterX == 3 || counterO == 3) {
            getWinner(counterX, counterO);
            earner = true;
        }
        return earner;
    }

    private void getWinner(int counterX, int counterO) {
        if (counterX == 3) {
            winner = 'X';
        }
        if (counterO == 3) {
            winner = 'O';
        }
    }

    private boolean finish() {
        boolean full = false;
        int numberBox = dimension * dimension;
        if (numberBox == numberMove) {
            full = true;
        }
        return full;
    }

    private void notifyListener(MyEvent event) {
        for (ITicTacToeUI ui : support) {
            ui.update(event);
        }
    }

    @Override
    public void addListener(ITicTacToeUI tictactoeUI) {
        support.add(tictactoeUI);
    }
}
