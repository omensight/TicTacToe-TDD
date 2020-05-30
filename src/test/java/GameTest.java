import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

public class GameTest {

    @Test
    public void check_GameAllowEntrance(){
        TicTacToe ticTacToe = new TicTacToeMatrix();
        Game game = new Game(ticTacToe);
//        Assert.assertTrue(game.check);
    }
}
