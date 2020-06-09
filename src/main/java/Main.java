import tictactoe.backend.ITicTacToe;
import tictactoe.backend.TicTacToe;

public class Main {
    public static void main(String[] args){
        ITicTacToe ticTacToe = new TicTacToe();
        Console console = new Console(ticTacToe);
        console.run();
    }
}
