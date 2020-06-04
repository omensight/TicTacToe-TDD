import org.w3c.dom.ranges.Range;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TicTacToe implements ITicTacToe {

    private char[][] board;
    private final static int DIMENSION = 3;
    private final static int MAX_TURNS = DIMENSION * DIMENSION;
    private final static char ODD_SYMBOL = 'X';
    private final static char EVEN_SYMBOL = 'O';
    private char currentPlayerSymbol;
    private char winner;
    private int turn;

    @Override
    public void create() {
        board = new char[DIMENSION][DIMENSION];
        currentPlayerSymbol = ODD_SYMBOL;
        winner = 0;
        turn = 1;
    }

    private boolean verifyBoxAvailability(int row, int column) {
        boolean isAvailable = false;
        boolean xBetweenTheBounds = row >= 0 && row < DIMENSION;
        boolean yBetweenTheBounds = column >= 0 && column < DIMENSION;
        if (xBetweenTheBounds && yBetweenTheBounds){
            isAvailable = board[row][column] == 0;
        }
        return isAvailable;
    }

    @Override
    public boolean markMove(int row, int column) {
        boolean marked = false;
        if (verifyBoxAvailability(row,column)){
            board[row][column] = currentPlayerSymbol;
            marked = true;
            changeTurn();
        }
        return marked;
    }

    private void changeTurn() {
        turn++;
        currentPlayerSymbol = turn%2 == 0 ? EVEN_SYMBOL : ODD_SYMBOL;
    }

    @Override
    public boolean checkTicTacToe() {
        boolean ticTacToe;
        ticTacToe = checkDiagonalTicTacToe();
        if (!ticTacToe){
            ticTacToe = checkHorizontalTicTacToe();
        }
        if (!ticTacToe){
            ticTacToe = checkVerticalWinner();
        }
        return ticTacToe;
    }


    private boolean checkDiagonalTicTacToe() {
        int rowLimit = DIMENSION-1;
        Character[] crescentDiagonal = new Character[3];
        Character[] decrescentDiagonal = new Character[3];
        for (int i = 0, j = rowLimit; i <= rowLimit && j >= 0;i++,j--){
            crescentDiagonal[i] = board[i][j];
            decrescentDiagonal[i] = board[i][i];
        }
        Set<Character> crescentFrequency = new HashSet<>(Arrays.asList(crescentDiagonal));
        Set<Character> decrescentFrequency = new HashSet<>(Arrays.asList(decrescentDiagonal));
        if (crescentFrequency.size()==1 && !crescentFrequency.contains('\u0000')){
            winner = (char) crescentFrequency.toArray()[0];
        }else if (decrescentFrequency.size()==1 && !decrescentFrequency.contains('\u0000')){
            winner = (char) decrescentFrequency.toArray()[0];
        }
        return winner != '\u0000';
    }

    private boolean checkHorizontalTicTacToe() {
        for (int i = 0; i < DIMENSION ; i++){
            Set<Character> row = new HashSet<>();
            for (int j = 0; j < DIMENSION ; j++){
                row.add(board[i][j]);
            }
            if (row.size() == 1 && !row.contains('\u0000')){
                winner = (char) row.toArray()[0];
                break;
            }
        }
        return winner != '\u0000';
    }

    private boolean checkVerticalWinner() {
        for (int i = 0; i < DIMENSION ; i++){
            Set<Character> row = new HashSet<>();
            for (int j = 0; j < DIMENSION ; j++){
                row.add(board[j][i]);
            }
            if (row.size() == 1 && !row.contains('\u0000')){
                winner = (char) row.toArray()[0];
                break;
            }
        }
        return winner != '\u0000';
    }

    @Override
    public char winner() {
        return winner;
    }

    @Override
    public boolean draw() {
        return turn >= MAX_TURNS && winner != 0;
    }

    @Override
    public char[][] getBoard() {
        char[][] copy = null;
        if (board != null){
            copy = Arrays.copyOf(board, DIMENSION);
        }
        return copy;
    }
}