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
    private boolean gameInProgress;
    private int turn;

    @Override
    public void create() {
        board = new char[DIMENSION][DIMENSION];
        currentPlayerSymbol = ODD_SYMBOL;
        turn = 1;
        gameInProgress = true;
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
        if (verifyBoxAvailability(row,column) && gameInProgress){
            board[row][column] = currentPlayerSymbol;
            marked = true;
            if (checkTicTacToe()){
                gameInProgress = false;
            }else {
                changeTurn();
            }
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
        boolean thereIsAWinner = false;
        int rowLimit = DIMENSION-1;
        Character[] crescentDiagonal = new Character[3];
        Character[] decrescentDiagonal = new Character[3];
        for (int i = 0, j = rowLimit; i <= rowLimit && j >= 0;i++,j--){
            crescentDiagonal[i] = board[i][j];
            decrescentDiagonal[i] = board[i][i];
        }
        Set<Character> crescentFrequency = new HashSet<>(Arrays.asList(crescentDiagonal));
        Set<Character> decrescentFrequency = new HashSet<>(Arrays.asList(decrescentDiagonal));
        thereIsAWinner = crescentFrequency.size()==1 && !crescentFrequency.contains('\u0000')
                || decrescentFrequency.size()==1 && !decrescentFrequency.contains('\u0000');

        return thereIsAWinner;
    }

    private boolean checkHorizontalTicTacToe() {
        boolean thereIsAWinner = false;
        for (int i = 0; i < DIMENSION ; i++){
            Set<Character> row = new HashSet<>();
            for (int j = 0; j < DIMENSION ; j++){
                row.add(board[i][j]);
            }
            if (row.size() == 1 && !row.contains('\u0000')){
                thereIsAWinner = true;
                break;
            }
        }
        return thereIsAWinner;
    }

    private boolean checkVerticalWinner() {
        boolean thereIsAWinner = false;
        for (int i = 0; i < DIMENSION ; i++){
            Set<Character> row = new HashSet<>();
            for (int j = 0; j < DIMENSION ; j++){
                row.add(board[j][i]);
            }
            if (row.size() == 1 && !row.contains('\u0000')){
                thereIsAWinner = true;
                break;
            }
        }
        return thereIsAWinner;
    }

    @Override
    public char winner() {
        char winner = checkTicTacToe() && !gameInProgress ? currentPlayerSymbol : 0;
        return winner;
    }

    @Override
    public boolean draw() {
        return turn >= MAX_TURNS && !checkTicTacToe();
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