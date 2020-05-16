import org.junit.Assert;
import org.junit.Test;

public class TicTacToeTest {

    @Test
    public void isGameInProgress_GameInProgress_True(){
        TicTacToe ticTacToe = new TicTacToe();
        Assert.assertTrue(ticTacToe.isGameInProgress());
    }


    //isGameInProgress_GameIsOver_False
    @Test
    public void isGameInProgress_GameIsOver_False(){
        TicTacToe ticTacToe = new TicTacToe();
        ticTacToe.checkBox(0,0);
        ticTacToe.checkBox(0,1);
        ticTacToe.checkBox(0,2);
        ticTacToe.checkBox(1,0);
        ticTacToe.checkBox(1,1);
        ticTacToe.checkBox(1,2);
        ticTacToe.checkBox(2,0);
        ticTacToe.checkBox(2,1);
        ticTacToe.checkBox(2,2);
        Assert.assertFalse(ticTacToe.isGameInProgress());
    }


    @Test
    public void verifyBoxAvailability_BoxAvailable_True(){
        TicTacToe ticTacToe = new TicTacToe();
        Assert.assertTrue(ticTacToe.verifyBoxAvailability(0,0));
    }

    @Test
    public void checkBox_Check_True(){
        TicTacToe ticTacToe = new TicTacToe();
        Assert.assertTrue(ticTacToe.checkBox(0,0));
    }

    @Test
    public void checkBox_CheckedBoxCanBeChecked_False(){
        TicTacToe ticTacToe = new TicTacToe();
        ticTacToe.checkBox(0,0);
        Assert.assertFalse(ticTacToe.checkBox(0, 0));
    }

    @Test
    public void checkWinner_ThereIsAWinner_True(){
        TicTacToe ticTacToe = new TicTacToe();
        ticTacToe.checkBox(0,0);
        ticTacToe.checkBox(1,1);
        ticTacToe.checkBox(1,0);
        ticTacToe.checkBox(1,2);
        ticTacToe.checkBox(2,0);
        Assert.assertNotEquals(0, ticTacToe.checkWinner());
    }

    @Test
    public void isGameInProgress_GameInProgressAfterTheFifthTurn_False(){
        TicTacToe ticTacToe = new TicTacToe();
        ticTacToe.checkBox(0,0);
        ticTacToe.checkBox(1,1);
        ticTacToe.checkBox(1,0);
        ticTacToe.checkBox(1,2);
        ticTacToe.checkBox(2,0);
        Assert.assertFalse(ticTacToe.isGameInProgress());
    }

    @Test
    public void checkWinner_WinnerAtTheFirstRow_True(){
        TicTacToe ticTacToe = new TicTacToe();
        ticTacToe.checkBox(0,0);
        ticTacToe.checkBox(1,0);
        ticTacToe.checkBox(0,1);
        ticTacToe.checkBox(1,1);
        ticTacToe.checkBox(0,2);

        Assert.assertNotEquals(0, ticTacToe.checkWinner());
    }

    @Test
    public void checkWinner_WinnerAtTheSecondRow_True(){
        TicTacToe ticTacToe = new TicTacToe();
        ticTacToe.checkBox(1,0);
        ticTacToe.checkBox(0,0);
        ticTacToe.checkBox(1,1);
        ticTacToe.checkBox(0,1);
        ticTacToe.checkBox(1,2);
        Assert.assertNotEquals(0, ticTacToe.checkWinner());
    }
}
