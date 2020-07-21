package tictactoe.backend;

import tictactoe.frontend.ITicTacToeUI;

public interface ITicTacToe extends IObservable{
    void create ();
    boolean markMove (int row, int column);
    boolean checkTicTacToe();
    char winner();
    boolean draw();
    char [][] getBoard();
    void addListener(ITicTacToeUI tictactoeUI);
}
