package tictactoe.backend;

import tictactoe.backend.ITicTacToe;
import tictactoe.backend.TicTacToe;
import tictactoe.frontend.ITicTacToeUI;

public class UltimateTicTacToe implements ITicTacToe {
    TicTacToe[] localBoards;
    public final static int LIMIT = 9;


    public UltimateTicTacToe() {
        initialize();
        create();
    }

    private void initialize() {
        localBoards = new TicTacToe[LIMIT];
        for (int i = 0; i<LIMIT; i++){
            localBoards[i] = new TicTacToe();
        }
    }

    @Override
    public void create() {
        for (TicTacToe ticTacToe :
                localBoards) {
            ticTacToe.create();
        }
    }

    @Override
    public boolean markMove(int row, int column) {
        boolean marked = false;
        if (!(row >= LIMIT || column >= LIMIT)){
            marked = true;
        }
        return marked;
    }

    @Override
    public boolean checkTicTacToe() {
        return false;
    }

    @Override
    public char winner() {
        return 0;
    }

    @Override
    public boolean draw() {
        return false;
    }

    @Override
    public char[][] getBoard() {
        return new char[LIMIT][LIMIT];
    }

    @Override
    public void addListener(ITicTacToeUI tictactoeUI) {

    }
}
