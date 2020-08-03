package tictactoe.controller;

public interface ITurnHandler<T> {

    void changeTurn();	
    T getTurn();	
    void reset();	

}