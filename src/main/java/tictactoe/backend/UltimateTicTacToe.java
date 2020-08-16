package tictactoe.backend;

import tictactoe.controller.ITurnHandler;
import tictactoe.controller.MyEvent;
import tictactoe.controller.TwoPlayerTurnHandler;
import tictactoe.frontend.ITicTacToeUI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UltimateTicTacToe implements IUltimateTicTacToe, ITicTacToeUI {
    private IObservableTicTacToe globalBoard;
    private IObservableTicTacToe[][] localBoards;
    public final static int QUADRANT_LIMIT = 3;
    public final static int LENGTH = QUADRANT_LIMIT * QUADRANT_LIMIT;
    private Pair<Integer, Integer> enabledQuadrant;

    private List<ITicTacToeUI> listeners;
    private ITurnHandler<Character> turnHandler;
    private MathQuadrantHelper mathQuadrantHelper;
    private MathBoardHelper mathBoardHelper;
    private List<Pair<Integer,Integer>> disabledQuadrants;
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
        disabledQuadrants = new ArrayList<>();
        for (int i = 0; i < QUADRANT_LIMIT; i++) {
            for (int j = 0; j < QUADRANT_LIMIT; j++) {
                IObservableTicTacToe ticTacToe = new TicTacToe(turnHandler);
                ticTacToe.addListener(this);
                localBoards[i][j] = ticTacToe;
            }
        }
        mathQuadrantHelper = new MathQuadrantHelper(QUADRANT_LIMIT);
        mathBoardHelper = new MathBoardHelper(QUADRANT_LIMIT);
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
            int quadrant = mathQuadrantHelper.getQuadrantFromGlobalCoordinates(row,column);
            Pair<Integer,Integer> localCoordinates = mathBoardHelper.getLocalCoordinates(row, column);
            marked = markMove(quadrant, localCoordinates.getX(), localCoordinates.getY());
        }
        return marked;
    }

    private boolean isQuadrantAbleToMark(Pair<Integer, Integer> quadrant) {

        return (quadrant.equals(enabledQuadrant)
                || enabledQuadrant.equals(new Pair<>(-1,-1)))
                && quadrant.getX() < QUADRANT_LIMIT;
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
                Pair<Integer,Integer> offset = mathBoardHelper.getOffset(i, j);
                ITicTacToe currentTicTacToe = localBoards[i][j];
                char[][] currentBoard = currentTicTacToe.getBoard();
                for (int k = 0; k < currentBoard.length; k++) {
                    System.arraycopy(currentBoard[k],
                            0,
                            ultimateTicTacToeBoard[offset.getX() + k],
                            offset.getY(),
                            currentBoard[k].length);
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
        Pair<Integer,Integer> quadrantComponents = mathQuadrantHelper.getQuadrantComponents(quadrant);
        boolean marked = false;
        if (isQuadrantAbleToMark(quadrantComponents)) {
            if (!disabledQuadrants.contains(quadrantComponents)){
                enabledQuadrant.set(row, column);
                ITicTacToe localBoard = localBoards[quadrantComponents.getX()][quadrantComponents.getY()];
                if (marked = localBoard.markMove(row, column)){
                    turnHandler.changeTurn();
                    if (disabledQuadrants.contains(new Pair<>(row, column))){
                        enabledQuadrant.set(-1,-1);
                    }
                }
            }

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
            Pair<Integer,Integer> quadrant = mathQuadrantHelper.getQuadrantComponents(index);
            switch (evt.getPropertyName()) {
                case "markMove": {
                    listeners.forEach(iTicTacToeUI -> {
                        notify(new MyEvent(this, "markMove", null, null));

                    });
                    localBoards[quadrant.getX()][quadrant.getY()].checkTicTacToe();
                    break;
                }
                case "winner": {
                    disabledQuadrants.add(quadrant);
                    globalBoard.markMove(quadrant.getX(), quadrant.getY());
                    notify(new MyEvent(this, "markMove", null, null));
                    enabledQuadrant.set(-1,-1);
                    break;
                }
            }
        }else if (source == globalBoard){
            notify(evt);
        }

    }

    private void notify(MyEvent event){
        listeners.forEach(iTicTacToeUI -> {
            iTicTacToeUI.update(event);
        });
    }
}