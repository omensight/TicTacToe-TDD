import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TicTacToeMatrix implements TicTacToe {
    public final static int BASE = 3;
    public final static int MAX_TURNS = BASE*BASE;
    public final static char ODD_PLAYER_SYMBOL = 'x';
    public final static char EVEN_PLAYER_SYMBOL = 'o';

    private char[][] board;
    private int currentTurn;
    private boolean gameInProgress;
    private char winner;

    public TicTacToeMatrix(){
        initialize();
    }

    @Override
    public boolean verifyBoxAvailability(int x, int y) {
        return board[x][y] == 0;
    }

    @Override
    public boolean checkBox(int x, int y) {
        boolean marked = false;
        if (verifyBoxAvailability(x,y)){
            board[x][y] = currentTurn%2 == 0 ? EVEN_PLAYER_SYMBOL : ODD_PLAYER_SYMBOL;
            marked = true;
            currentTurn++;

            checkVerticalWinner();
            if(winner == 0){
                checkHorizontalWinner();
                if(winner == 0){
                    checkDiagonalWinner();
                }
            }

            if (winner!=0){
                gameInProgress = false;
            }
        }
        return marked;
    }

    private void checkDiagonalWinner() {
        int rowLimit = (int) Math.sqrt(MAX_TURNS)-1;
        Character[] crescentDiagonal = new Character[3];
        Character[] decrescentDiagonal = new Character[3];
        for (int i = 0, j = rowLimit; i<=rowLimit && j >= 0;i++,j--){
            crescentDiagonal[i] = board[i][j];
            decrescentDiagonal[i] = board[i][i];
        }
        Set<Character> crescentFrequency = new HashSet<>(Arrays.asList(crescentDiagonal));
        Set<Character> decrescentFrequency = new HashSet<>(Arrays.asList(decrescentDiagonal));
        if (crescentFrequency.size()==1 && !crescentFrequency.contains('\u0000')){
            winner = (char) crescentFrequency.toArray()[0];
        }else if (decrescentFrequency.size()==1 && !crescentFrequency.contains('\u0000')){
            winner = (char) decrescentFrequency.toArray()[0];
        }
    }

    private void checkHorizontalWinner() {
        for (int i = 0; i < BASE ; i++){
            Set<Character> row = new HashSet<>();
            for (int j = 0; j < BASE ; j++){
                row.add(board[i][j]);
            }
            if (row.size() == 1 && !row.contains('\u0000')){
                winner = (char) row.toArray()[0];
                break;
            }
        }
    }

    private void checkVerticalWinner() {
        for (int i = 0; i < BASE ; i++){
            Set<Character> row = new HashSet<>();
            for (int j = 0; j < BASE ; j++){
                row.add(board[j][i]);
            }
            if (row.size() == 1 && !row.contains('\u0000')){
                winner = (char) row.toArray()[0];
                break;
            }
        }
    }

    @Override
    public char checkWinner() {
        return winner;
    }

    @Override
    public boolean isGameInProgress() {
        return gameInProgress;
    }

    public char[][] peek(){
        return board.clone();
    }

    @Override
    public void stop() {
        gameInProgress = false;
    }

    @Override
    public char getSymbolOfCurrentPlayer() {
        char playerSymbol = 0;
        if (gameInProgress){
            playerSymbol = currentTurn % 2 == 0 ? EVEN_PLAYER_SYMBOL : ODD_PLAYER_SYMBOL;
        }
        return playerSymbol;
    }

    @Override
    public char[][] peekBoard() {
        return board;
    }

    @Override
    public int getTurn() {
        return currentTurn;
    }

    @Override
    public void initialize() {
        currentTurn = 1;
        board = new char[BASE][BASE];
        gameInProgress = true;
        winner = 0;
    }
}