package tictactoe.backend;

import tictactoe.frontend.ITicTacToeUI;

interface IObservable {
    void addListener(ITicTacToeUI iTicTacToeUI);
}
