import tictactoe.backend.ITicTacToe;
import tictactoe.backend.TicTacToe;
import tictactoe.frontend.Console;
import tictactoe.frontend.GUI;
import tictactoe.frontend.ITicTacToeUI;

public class Main {

    public static void main(String[] args) {
        ITicTacToe ticTacToe  = new TicTacToe();
        ITicTacToeUI console = new Console(ticTacToe);
        ITicTacToeUI gui = new GUI(ticTacToe);

        gui.run();
        console.run();
    }
}
