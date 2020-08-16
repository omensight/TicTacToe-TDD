package tictactoe.frontend.util;

import tictactoe.backend.IUltimateTicTacToe;
import tictactoe.backend.MathQuadrantHelper;
import tictactoe.backend.Pair;

public class UltimateBoardRenderer implements ITicTacToeBoardRenderer {

    private final IUltimateTicTacToe ultimateTicTacToe;
    private final MathQuadrantHelper mathQuadrantHelper;
    //Magenta X
    public static final String X_COLOR  = "\u001b[35m";

    //Cyan O
    public static final String O_COLOR = "\u001b[36m";

    //Yellow X
    public static final String ENABLED_QUADRANT_COLOR  = "\u001B[33m";

    //Yellow X
    public static final String SIMPLE_MARKED_COLOR  = "\u001B[37m";

    public UltimateBoardRenderer(IUltimateTicTacToe ultimateTicTacToe) {
        this.ultimateTicTacToe = ultimateTicTacToe;
        this.mathQuadrantHelper = new MathQuadrantHelper(3);
    }

    @Override
    public IBoxUI[][] getRenderableBoard() {
        IBoxUI[][] res = new BoxUI[9][9];
        char[][] bigBoard = ultimateTicTacToe.getBoard();
        char[][] globalBoard = ultimateTicTacToe.getGlobalBoard();
        Pair<Integer,Integer> enabledQuadrant = ultimateTicTacToe.getEnabledQuadrant();
        for(int row = 0; row < bigBoard.length; row++){
            for (int column = 0; column < bigBoard[row].length; column++) {
                Pair<Integer,Integer> currentQuadrant = mathQuadrantHelper.getQuadrantFromBigCoordinates(row, column);
                char globalSymbol = globalBoard[currentQuadrant.getX()][currentQuadrant.getY()];
                char currentSymbol = bigBoard[row][column];
                IBoxUI boxUI;
                if (currentQuadrant.equals(new Pair<>(-1,-1))){
                    boxUI = getEnabledQuadrant(currentSymbol);
                }else if (globalSymbol != 0){
                    boxUI = getOccupiedBox(currentSymbol, globalSymbol);
                }else if (enabledQuadrant.equals(currentQuadrant)){
                    boxUI = getEnabledQuadrant(currentSymbol);
                }else if (currentSymbol != 0){
                    boxUI = getMarkedBox(currentSymbol);
                }else {
                    boxUI = getEmptyBox();
                }
                res[row][column] = boxUI;
            }
        }
        return res;
    }

    private IBoxUI getMarkedBox(char currentSymbol) {
        return new BoxUI(SIMPLE_MARKED_COLOR, currentSymbol, getColorBasedOnSymbol(currentSymbol));
    }

    private IBoxUI getEmptyBox() {
        return new BoxUI(SIMPLE_MARKED_COLOR, ' ', SIMPLE_MARKED_COLOR);
    }

    private IBoxUI getEnabledQuadrant(char currentSymbol) {
        if (currentSymbol == 0){
            currentSymbol = ' ';
        }
        return new BoxUI(UltimateBoardRenderer.ENABLED_QUADRANT_COLOR, currentSymbol, getColorBasedOnSymbol(currentSymbol));
    }

    private IBoxUI getOccupiedBox(char symbol, char occupiedSymbol) {
        String color = occupiedSymbol == 'X' ? X_COLOR : O_COLOR;
        return new BoxUI(color, symbol == 0? ' ' : symbol, getColorBasedOnSymbol(symbol));
    }

    private String getColorBasedOnSymbol(char symbol){
        String res = SIMPLE_MARKED_COLOR;
        if (symbol == 'X'){
            res = X_COLOR;
        }else if (symbol == 'O'){
            res = O_COLOR;
        }
        return res;
    }
}
