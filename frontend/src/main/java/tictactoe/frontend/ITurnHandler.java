package tictactoe.frontend;

interface ITurnHandler<T> {

    void changeTurn();
    T getTurn();
    void reset();

}
