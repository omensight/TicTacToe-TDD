package tictactoe.backend;

import tictactoe.controller.ITurnHandler;
import tictactoe.controller.MyEvent;
import tictactoe.controller.TwoPlayerTurnHandler;
import tictactoe.frontend.ITicTacToeUI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UltimateTicTacToe implements IUltimateTicTacToe, ITicTacToeUI {
    private ITicTacToe globalBoard;
    private ITicTacToe[][] localBoards;
    public final static int QUADRANT_LIMIT = 3;
    public final static int LENGTH = QUADRANT_LIMIT * QUADRANT_LIMIT;
    private Pair<Integer, Integer> enabledQuadrant;

    private List<ITicTacToeUI> listeners;
    private ITurnHandler<Character> turnHandler;
    private QuadrantMathHelper quadrantMathHelper;

    public UltimateTicTacToe() {
        initialize();
        create();
    }

    private void initialize() {
        localBoards = new TicTacToe[QUADRANT_LIMIT][QUADRANT_LIMIT];
        turnHandler = new TwoPlayerTurnHandler<>('X', 'O');
        globalBoard = new TicTacToe(turnHandler);
        globalBoard.addListener(this);
        listeners = new ArrayList<>();
        for (int i = 0; i < QUADRANT_LIMIT; i++) {
            for (int j = 0; j < QUADRANT_LIMIT; j++) {
                ITicTacToe ticTacToe = new TicTacToe(turnHandler);
                ticTacToe.addListener(this);
                localBoards[i][j] = ticTacToe;
            }
        }
        quadrantMathHelper = new QuadrantMathHelper(QUADRANT_LIMIT);
    }

    @Override
    public void create() {
        enabledQuadrant = new Pair<>();
        enabledQuadrant.set(-1, -1);
        globalBoard.create();
        turnHandler.reset();
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
        boolean marked;
        if (row >= LENGTH || column >= LENGTH) {
            marked = false;
        } else {
            int xQuadrant = row / QUADRANT_LIMIT;
            int yQuadrant = column / QUADRANT_LIMIT;
            int quadrant = xQuadrant * QUADRANT_LIMIT + yQuadrant;
            int ticTacToeRow = row % QUADRANT_LIMIT;
            int ticTacToeColumn = column % QUADRANT_LIMIT;
            marked = markMove(quadrant, ticTacToeRow, ticTacToeColumn);
        }
        return marked;
    }

    private boolean isQuadrantAbleToMark(int enabledQuadrantCandidate) {
        int xQuadrant = enabledQuadrantCandidate / QUADRANT_LIMIT;
        int yQuadrant = enabledQuadrantCandidate % QUADRANT_LIMIT;
        return (xQuadrant == enabledQuadrant.getX() && yQuadrant == enabledQuadrant.getY())
                || (-1 == enabledQuadrant.getX() && -1 == enabledQuadrant.getY())
                && xQuadrant < QUADRANT_LIMIT;
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
        var test = localBoards[1][1];
        for (int i = 0; i < QUADRANT_LIMIT; i++) {
            for (int j = 0; j < QUADRANT_LIMIT; j++) {
                int xOffset = i * QUADRANT_LIMIT;
                int yOffset = j * QUADRANT_LIMIT;
                ITicTacToe currentTicTacToe = localBoards[i][j];
                char[][] currentBoard = currentTicTacToe.getBoard();
                for (int k = 0; k < currentBoard.length; k++) {
                    for (int l = 0; l < currentBoard[k].length; l++) {
                        ultimateTicTacToeBoard[xOffset + k][yOffset + l] = currentBoard[k][l];
                    }
                }
            }
        }
        return ultimateTicTacToeBoard;
    }

    @Override
    public void addListener(ITicTacToeUI ticTacToeUI) {
        listeners.add(ticTacToeUI);
        globalBoard.addListener(ticTacToeUI);
    }

    @Override
    public void run() {

    }

    private List<ITicTacToe> getLocalBoardsAsList() {
        List<ITicTacToe> ticTacToeList = new ArrayList<>();
        for (ITicTacToe[] ticTacToeRow :
                localBoards) {
            ticTacToeList.addAll(Arrays.asList(ticTacToeRow));
        }
        return ticTacToeList;
    }

    @Override
    public boolean markMove(int quadrant, int row, int column) {
        int xQuadrant = quadrant / QUADRANT_LIMIT;
        int yQuadrant = quadrant % QUADRANT_LIMIT;
        boolean marked = false;
        if (isQuadrantAbleToMark(quadrant)) {
            ITicTacToe localBoard = localBoards[xQuadrant][yQuadrant];
            if (marked = localBoard.markMove(row, column)){
                turnHandler.changeTurn();
            }
            enabledQuadrant.set(row, column);
        }
        return marked;
    }

    @Override
    public char[][] getGlobalBoard(){
        return globalBoard.getBoard();
    }

    @Override
    public Pair<Integer, Integer> getEnabledQuadrant() {
        return new Pair<>(enabledQuadrant.getX(), enabledQuadrant.getY());
    }

    @Override
    public void update(MyEvent evt) {
        Object source = evt.getSource();
        int index = getLocalBoardsAsList().indexOf(source);
        if (index != -1) {
            switch (evt.getPropertyName()) {
                case "markMove": {
                    listeners.forEach(iTicTacToeUI -> {
                        iTicTacToeUI.update(
                                new MyEvent(this, "markMove", null, null)
                        );

                    });
                    int x = index / QUADRANT_LIMIT;
                    int y = index % QUADRANT_LIMIT;
                    localBoards[x][y].checkTicTacToe();
                    break;
                }
                case "winner": {
                    int x = index / QUADRANT_LIMIT;
                    int y = index % QUADRANT_LIMIT;
                    globalBoard.markMove(x, y);
                    break;
                }
            }
        }else if (source == globalBoard){
            System.out.println("Event");
            listeners.forEach(ticTacToeUI->{
                ticTacToeUI.update(evt);
            });
        }

    }
}