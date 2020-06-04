import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GameTest {
    Game game;
    @Before
    public void check_GameAllowEntrance(){
        TicTacToe ticTacToe = new TicTacToe();
        game = new Game(ticTacToe);
        game.startGame();
    }
}
