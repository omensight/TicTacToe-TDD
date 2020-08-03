import tictactoe.backend.ITicTacToe;
import tictactoe.backend.IUltimateTicTacToe;
import tictactoe.backend.TicTacToe;
import tictactoe.backend.UltimateTicTacToe;
import tictactoe.controller.ITurnHandler;
import tictactoe.controller.TwoPlayerTurnHandler;
import tictactoe.frontend.Console;
import tictactoe.frontend.GUI;
import tictactoe.frontend.ITicTacToeUI;

public class Main {

    public static void main(String[] args) {
        IUltimateTicTacToe ticTacToe  = new UltimateTicTacToe();
        ITicTacToeUI console = new Console(ticTacToe);
        ITicTacToeUI gui = new GUI(ticTacToe);
        gui.run();
        console.run();
    }
}
