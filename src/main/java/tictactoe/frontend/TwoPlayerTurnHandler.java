package tictactoe.frontend;

class TwoPlayerTurnHandler<T> implements ITurnHandler<T> {
    private final T playerOne;
    private final T playerTwo;
    private boolean playerOneTurn;

    public TwoPlayerTurnHandler(T playerOne, T playerTwo){
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        playerOneTurn = true;

    }

    @Override
    public void changeTurn(){
        playerOneTurn = !playerOneTurn;
    }

    @Override
    public T getTurn() {
        return playerOneTurn ? playerOne : playerTwo;
    }


    @Override
    public void reset() {
        playerOneTurn = true;
    }
}
