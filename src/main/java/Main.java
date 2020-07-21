import tictactoe.backend.ITicTacToe;
import tictactoe.backend.TicTacToe;
import tictactoe.frontend.Console;
import tictactoe.frontend.GUI;
import tictactoe.frontend.ITicTacToeUI;


public class Main {
    public static void main(String[] args) {
        ITicTacToe ticTacToe = new TicTacToe();
        ITicTacToeUI ui = new GUI(ticTacToe);
        ITicTacToeUI console = new Console(ticTacToe);
        ui.run();
        console.run();
    }
}
