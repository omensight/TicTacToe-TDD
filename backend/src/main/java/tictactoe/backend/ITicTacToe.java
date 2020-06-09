package tictactoe.backend;

public interface ITicTacToe {
    /**
     * This method is used to create or initialize the backend.TicTacToe game.
     */
    void create();

    /**
     * This method is used to mark a game box
     * @param row is the row position of the box to mark
     * @param column is the column position of the box to mark
     * @return true if the box was marked
     */
    boolean markMove(int row, int column);

    /**
     * This method is used to check if there is Tic Tac Toe
     * @return true if there is
     */
    boolean checkTicTacToe();

    /**
     * This method is used to check who is the winner
     * @return 'X' or 'O' depending who is the winner
     */
    char winner();

    /**
     * This method is used to verify if there is a draw
     * @return <code>true</code> if there is a Draw
     */
    boolean draw();

    /**
     * This method is used to get the board
     * @return the board of the Tic Tac Toe Game where each box contains a <code>'X'</code> or an <code>'O'</code> or 
     */
    char[][] getBoard();
}