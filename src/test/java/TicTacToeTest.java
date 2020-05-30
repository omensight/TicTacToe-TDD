import org.junit.Assert;
import org.junit.Test;

public class TicTacToeTest {

    @Test
    public void isGameInProgress_GameInProgress_True(){
        TicTacToe ticTacToe = new TicTacToeArray();
        Assert.assertTrue(ticTacToe.isGameInProgress());
    }


    //isGameInProgress_GameIsOver_False
    @Test
    public void isGameInProgress_GameIsOver_False(){
        TicTacToe ticTacToe = new TicTacToeArray();
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
        TicTacToe ticTacToe = new TicTacToeArray();
        Assert.assertTrue(ticTacToe.verifyBoxAvailability(0,0));
    }

    @Test
    public void checkBox_Check_True(){
        TicTacToe ticTacToe = new TicTacToeArray();
        Assert.assertTrue(ticTacToe.checkBox(0,0));
    }

    @Test
    public void checkBox_CheckedBoxCanBeChecked_False(){
        TicTacToe ticTacToe = new TicTacToeArray();
        ticTacToe.checkBox(0,0);
        Assert.assertFalse(ticTacToe.checkBox(0, 0));
    }

    @Test
    public void checkWinner_ThereIsAWinner_True(){
        TicTacToe ticTacToe = new TicTacToeArray();
        ticTacToe.checkBox(0,0);
        ticTacToe.checkBox(1,1);
        ticTacToe.checkBox(1,0);
        ticTacToe.checkBox(1,2);
        ticTacToe.checkBox(2,0);
        Assert.assertNotEquals(0, ticTacToe.checkWinner());
    }

    @Test
    public void isGameInProgress_GameInProgressAfterTheFifthTurn_False(){
        TicTacToe ticTacToe = new TicTacToeArray();
        ticTacToe.checkBox(0,0);
        ticTacToe.checkBox(1,1);
        ticTacToe.checkBox(1,0);
        ticTacToe.checkBox(1,2);
        ticTacToe.checkBox(2,0);
        Assert.assertFalse(ticTacToe.isGameInProgress());
    }

    @Test
    public void checkWinner_WinnerAtTheFirstRow_True(){
        TicTacToe ticTacToe = new TicTacToeArray();
        ticTacToe.checkBox(0,0);
        ticTacToe.checkBox(1,0);
        ticTacToe.checkBox(0,1);
        ticTacToe.checkBox(1,1);
        ticTacToe.checkBox(0,2);

        Assert.assertNotEquals(0, ticTacToe.checkWinner());
    }

    @Test
    public void checkWinner_WinnerAtTheSecondRow_True(){
        TicTacToe ticTacToe = new TicTacToeArray();
        ticTacToe.checkBox(1,0);
        ticTacToe.checkBox(0,0);
        ticTacToe.checkBox(1,1);
        ticTacToe.checkBox(0,1);
        ticTacToe.checkBox(1,2);
        Assert.assertNotEquals(0, ticTacToe.checkWinner());
    }

    @Test
    public void stop_GameInProgressAfter_True(){
        TicTacToe ticTacToe = new TicTacToeMatrix();
        ticTacToe.stop();
        Assert.assertFalse(ticTacToe.isGameInProgress());
    }

    @Test
    public void whoPlays_XPlaysAtTheFirstTurn_True(){
        TicTacToe ticTacToe = new TicTacToeMatrix();
        Assert.assertEquals('x',ticTacToe.getSymbolOfCurrentPlayer());
    }

    @Test
    public void getSymbolOfCurrentPlayer_OPlaysAtTheSixthTurn_True(){
        TicTacToe ticTacToe = new TicTacToeMatrix();
        ticTacToe.checkBox(1,1);
        ticTacToe.checkBox(1,2);
        ticTacToe.checkBox(1,0);
        ticTacToe.checkBox(2,2);
        ticTacToe.checkBox(0,2);
        Assert.assertEquals('o',ticTacToe.getSymbolOfCurrentPlayer());
    }

    @Test
    public void getSymbolOfCurrentPlayer_XPlaysAtTheSixthTurn_False(){
        TicTacToe ticTacToe = new TicTacToeMatrix();
        ticTacToe.checkBox(1,1);
        ticTacToe.checkBox(1,2);
        ticTacToe.checkBox(1,0);
        ticTacToe.checkBox(2,2);
        ticTacToe.checkBox(0,2);
        Assert.assertNotEquals('x',ticTacToe.getSymbolOfCurrentPlayer());
    }


    @Test
    public void peekBoard_True(){
        TicTacToe ticTacToe = new TicTacToeMatrix();
        Assert.assertNotNull(ticTacToe.peekBoard());
    }

    @Test
    public void turn_Is1AtBeginning_True(){
        TicTacToe ticTacToe = new TicTacToeMatrix();
        Assert.assertEquals(1, ticTacToe.getTurn());
    }

    @Test
    public void turn_Is5AfterTheFourthCheck_True(){
        TicTacToe ticTacToe = new TicTacToeMatrix();
        ticTacToe.checkBox(0,0);
        ticTacToe.checkBox(0,1);
        ticTacToe.checkBox(0,2);
        ticTacToe.checkBox(1,1);
        Assert.assertEquals(5, ticTacToe.getTurn());
    }

    @Test
    public void initialize_TurnIs1AfterResetAt5thTurn_True(){
        TicTacToe ticTacToe = new TicTacToeMatrix();
        ticTacToe.checkBox(0,0);
        ticTacToe.checkBox(0,1);
        ticTacToe.checkBox(0,2);
        ticTacToe.checkBox(1,1);
        ticTacToe.checkBox(1,2);
        ticTacToe.initialize();
        Assert.assertEquals(1, ticTacToe.getTurn());
    }
}
