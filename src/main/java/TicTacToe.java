public interface TicTacToe {
    boolean verifyBoxAvailability(int x, int y);
    boolean checkBox(int x, int y);
    char checkWinner();
    boolean isGameInProgress();
    void stop();
    char getSymbolOfCurrentPlayer();
    char[][] peekBoard();
    int getTurn();
    void initialize();
}
