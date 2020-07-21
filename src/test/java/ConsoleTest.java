import org.junit.Before;
import tictactoe.backend.ITicTacToe;
import tictactoe.backend.TicTacToe;
import tictactoe.frontend.Console;

public class ConsoleTest {
    Console console;
    @Before
    public void check_GameAllowEntrance(){
        ITicTacToe ticTacToe = new TicTacToe();
        console = new Console(ticTacToe);
        console.run();

    }
}
