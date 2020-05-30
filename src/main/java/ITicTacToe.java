interface ITicTacToe {
    void create();
    boolean markMove(int row, int column);
    boolean checkTicTacToe();
    char winner();
    boolean draw();
    char[][] getBoard();
}