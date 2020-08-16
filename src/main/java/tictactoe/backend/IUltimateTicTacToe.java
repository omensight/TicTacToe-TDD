package tictactoe.backend;

public interface IUltimateTicTacToe extends IObservableTicTacToe{
    boolean markMove(int quadrant, int row, int column);
    char[][] getGlobalBoard();
    Pair<Integer, Integer> getEnabledQuadrant();
}
