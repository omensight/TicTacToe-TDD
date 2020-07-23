package tictactoe.backend;

import tictactoe.controller.MyEvent;
import tictactoe.frontend.ITicTacToeUI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UltimateTicTacToe implements ITicTacToe, ITicTacToeUI {
    ITicTacToe[][] localBoards;
    ITicTacToe globalBoard;
    public final static int QUADRANT_LIMIT = 3;
    public final static int LENGTH = QUADRANT_LIMIT * QUADRANT_LIMIT;

    private int quadrantXEnabled;
    private int quadrantYEnabled;

    private List<ITicTacToeUI> listeners;

    public UltimateTicTacToe() {
        initialize();
        create();
    }

    private void initialize() {
        localBoards = new TicTacToe[QUADRANT_LIMIT][QUADRANT_LIMIT];
        globalBoard = new TicTacToe();
        listeners = new ArrayList<>();
        for (int i = 0; i < QUADRANT_LIMIT; i++) {
            for (int j = 0; j < QUADRANT_LIMIT; j++) {
                ITicTacToe ticTacToe = new TicTacToe();
                ticTacToe.addListener(this);
                localBoards[i][j] = ticTacToe;
            }
        }
    }

    @Override
    public void create() {
        quadrantXEnabled = quadrantYEnabled = -1;
        globalBoard.create();
        for (ITicTacToe[] localBoard :
                localBoards) {
            for (ITicTacToe ticTacToe :
                    localBoard) {
                ticTacToe.create();
            }
        }
    }

    @Override
    public boolean markMove(int row, int column) {
        boolean marked = false;
        if (!(row >= LENGTH || column >= LENGTH)) {
            int quadrantRow = row / QUADRANT_LIMIT;
            int quadrantColumn = column / QUADRANT_LIMIT;

            if (isQuadrantAbleToMark(quadrantRow, quadrantColumn)) {
                int ticTacToeRow = row % QUADRANT_LIMIT;
                int ticTacToeColumn = column % QUADRANT_LIMIT;
                ITicTacToe ticTacToe = localBoards[quadrantRow][quadrantColumn];
                marked = ticTacToe.markMove(ticTacToeRow, ticTacToeColumn);
                quadrantXEnabled = ticTacToeRow;
                quadrantYEnabled = ticTacToeColumn;
            }
        }
        return marked;
    }

    private boolean isQuadrantAbleToMark(int row, int column) {
        return (quadrantXEnabled == row && quadrantYEnabled == column)
                || (quadrantXEnabled == -1 && quadrantYEnabled == -1);
    }

    @Override
    public boolean checkTicTacToe() {
        return globalBoard.checkTicTacToe();
    }

    @Override
    public char winner() {
        return globalBoard.winner();
    }

    @Override
    public boolean draw() {
        return globalBoard.draw();
    }

    @Override
    public char[][] getBoard() {
        char[][] ultimateTicTacToeBoard = new char[LENGTH][LENGTH];

        for (int i = 0; i < QUADRANT_LIMIT; i++) {
            for (int j = 0; j < QUADRANT_LIMIT; j++) {
                int xOffset = i * QUADRANT_LIMIT;
                int yOffset = j * QUADRANT_LIMIT;
                ITicTacToe currentTicTacToe = localBoards[i][j];
                char[][] currentBoard = currentTicTacToe.getBoard();
                for (int k = 0; k < currentBoard.length; k++) {
                    char[] rowSymbols = currentBoard[k];
                    System.arraycopy(currentBoard[k], 0, ultimateTicTacToeBoard[xOffset + k], yOffset, rowSymbols.length);
                }
            }
        }
        return ultimateTicTacToeBoard;
    }

    @Override
    public void addListener(ITicTacToeUI ticTacToeUI) {
        listeners.add(ticTacToeUI);
    }

    @Override
    public void run() {

    }

    @Override
    public void update(MyEvent evt) {
        int index = getLocalBoardsAsList().indexOf(evt.getSource());
        if (index != -1) {
            switch (evt.getPropertyName()) {
                case "markMove": {
                    listeners.forEach(iTicTacToeUI -> {
                        iTicTacToeUI.update(
                                new MyEvent(this, "markMove", null, null)
                        );
                    });
                    break;
                }
                case "winner": {
                    int x = index / QUADRANT_LIMIT;
                    int y = index % QUADRANT_LIMIT;
                    globalBoard.markMove(x,y);
                    break;
                }
            }
        }

    }

    private List<ITicTacToe> getLocalBoardsAsList() {
        List<ITicTacToe> ticTacToeList = new ArrayList<>();
        for (ITicTacToe[] ticTacToeRow :
                localBoards) {
            ticTacToeList.addAll(Arrays.asList(ticTacToeRow));
        }
        return ticTacToeList;
    }
}
