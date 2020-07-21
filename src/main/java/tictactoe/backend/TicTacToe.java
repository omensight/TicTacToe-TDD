package tictactoe.backend;

import tictactoe.frontend.ITicTacToeUI;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.*;

public class TicTacToe implements ITicTacToe {

    private final char[][] board;
    private final static int DIMENSION = 3;
    private final static int MAX_TURNS = DIMENSION * DIMENSION;
    private final static char ODD_SYMBOL = 'X';
    private final static char EVEN_SYMBOL = 'O';
    private final static char EMPTY = '\u0000';
    private char currentPlayerSymbol;
    private boolean gameInProgress;
    private int turn;
    private PropertyChangeSupport support;


    public TicTacToe(){
        board = new char[DIMENSION][DIMENSION];
        support = new PropertyChangeSupport(this);
        create();
    }

    @Override
    public void create() {
        for (int i = 0 ; i < DIMENSION; i++){
            for (int j = 0 ; j < DIMENSION; j++){
                board[i][j] = EMPTY;
            }
        }
        currentPlayerSymbol = ODD_SYMBOL;
        turn = 1;
        gameInProgress = true;
        notifyEvent(new PropertyChangeEvent(this, "create", getBoard(), getBoard()));
    }

    private void notifyEvent(PropertyChangeEvent event) {
        support.firePropertyChange(event);
    }

    private boolean verifyBoxAvailability(int row, int column) {
        boolean isAvailable = false;
        boolean xBetweenTheBounds = row >= 0 && row < DIMENSION;
        boolean yBetweenTheBounds = column >= 0 && column < DIMENSION;
        if (xBetweenTheBounds && yBetweenTheBounds){
            isAvailable = board[row][column] == EMPTY;
        }
        return isAvailable;
    }

    @Override
    public boolean markMove(int row, int column) {
        boolean marked = false;
        if (verifyBoxAvailability(row,column) && gameInProgress){
            board[row][column] = currentPlayerSymbol;
            marked = true;
            if (checkTicTacToe()){
                gameInProgress = false;
            }else {
                changeTurn();
            }
            notifyEvent(new PropertyChangeEvent(this, "markMove", getBoard(), getBoard()));
        }
        return marked;
    }

    private void changeTurn() {
        turn++;
        currentPlayerSymbol = turn%2 == 0 ? EVEN_SYMBOL : ODD_SYMBOL;
    }

    @Override
    public boolean checkTicTacToe() {
        boolean ticTacToe;
        ticTacToe = checkDiagonalTicTacToe();
        if (!ticTacToe){
            ticTacToe = checkHorizontalTicTacToe();
        }
        if (!ticTacToe){
            ticTacToe = checkVerticalWinner();
        }
        if (ticTacToe){
            support.firePropertyChange(new PropertyChangeEvent(this, "winner", false, true));
        }
        return ticTacToe;
    }

    private boolean checkDiagonalTicTacToe() {
        boolean thereIsAWinner;
        Set<Character> crescentFrequency = new HashSet<>();
        Set<Character> decrescentFrequency = new HashSet<>();
        for (int i = 0, j = DIMENSION-1; i < DIMENSION && j >= 0;i++,j--){
            crescentFrequency.add(board[i][j]);
            decrescentFrequency.add(board[i][i]);
        }

        thereIsAWinner = crescentFrequency.size()==1 && !crescentFrequency.contains(EMPTY)
                || decrescentFrequency.size()==1 && !decrescentFrequency.contains(EMPTY);

        return thereIsAWinner;
    }

    private boolean checkHorizontalTicTacToe() {
        boolean thereIsAWinner = false;
        for (int i = 0; i < DIMENSION ; i++){
            Set<Character> row = new HashSet<>();
            for (int j = 0; j < DIMENSION ; j++){
                row.add(board[i][j]);
            }
            if (row.size() == 1 && !row.contains(EMPTY)){
                thereIsAWinner = true;
                break;
            }
        }
        return thereIsAWinner;
    }

    private boolean checkVerticalWinner() {
        boolean thereIsAWinner = false;
        for (int i = 0; i < DIMENSION ; i++){
            Set<Character> row = new HashSet<>();
            for (int j = 0; j < DIMENSION ; j++){
                row.add(board[j][i]);
            }
            if (row.size() == 1 && !row.contains(EMPTY)){
                thereIsAWinner = true;
                break;
            }
        }
        return thereIsAWinner;
    }

    @Override
    public char winner() {
        char winner = 0;
        if (!gameInProgress && turn <= MAX_TURNS){
            winner = turn % 2 == 0 ? EVEN_SYMBOL : ODD_SYMBOL;
        }
        return winner;
    }

    @Override
    public boolean draw() {
        return turn > MAX_TURNS && !checkTicTacToe();
    }

    @Override
    public char[][] getBoard() {
        char[][] copy = new char[DIMENSION][DIMENSION];
        if (board != null){
            copy = Arrays.copyOf(board, DIMENSION);
        }
        for (int i = 0; i < DIMENSION; i++){
            copy[i] = Arrays.copyOf(board[i], board[i].length);
        }

        return copy;
    }

    @Override
    public void addListener(ITicTacToeUI iTicTacToeUI) {
        support.addPropertyChangeListener((PropertyChangeListener) iTicTacToeUI);
    }
}