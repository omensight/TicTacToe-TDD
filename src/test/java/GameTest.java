import org.junit.Before;

public class GameTest {
    Game game;
    @Before
    public void check_GameAllowEntrance(){
        TicTacToe ticTacToe = new TicTacToe();
        game = new Game(ticTacToe);
        game.run();
    }
}
